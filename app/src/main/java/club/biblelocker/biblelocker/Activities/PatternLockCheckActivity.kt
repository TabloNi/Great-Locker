package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import club.biblelocker.biblelocker.R
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.andrognito.patternlockview.utils.ResourceUtils
import com.andrognito.rxpatternlockview.RxPatternLockView
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent
import kotlinx.android.synthetic.main.activity_pattern_lock_check.*

class PatternLockCheckActivity : AppCompatActivity() {

    var pattern = ""
    var pattern_check = false

    fun getPatternPass() : String {
        return getSharedPreferences("pattern_pass",Activity.MODE_PRIVATE).getString("pattern_pass","")
    }

    fun savePass() {
        getSharedPreferences("pass_way",Activity.MODE_PRIVATE).edit().putString("pass_way","pattern").commit()
    }

    fun removePass()
    {
        getSharedPreferences("pass_way",Activity.MODE_PRIVATE).edit().remove("pass_way").commit()
    }

    fun removePreferences()
    {
        getSharedPreferences("pattern_pass",Activity.MODE_PRIVATE).edit().remove("pattern_pass").commit()
    }



    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pattern_lock_check)

        pattern_lock_view_check.dotCount = 3
        pattern_lock_view_check.dotNormalSize = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size) as Int
        pattern_lock_view_check.dotSelectedSize = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size) as Int
        pattern_lock_view_check.pathWidth = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width) as Int
        pattern_lock_view_check.isAspectRatioEnabled = true
        pattern_lock_view_check.aspectRatio = PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS
        pattern_lock_view_check.setViewMode(PatternLockView.PatternViewMode.CORRECT)
        pattern_lock_view_check.dotAnimationDuration = 150
        pattern_lock_view_check.pathEndAnimationDuration = 100
        pattern_lock_view_check.correctStateColor = ResourceUtils.getColor(this, R.color.white)
        pattern_lock_view_check.isInStealthMode = false;
        pattern_lock_view_check.isTactileFeedbackEnabled = true;
        pattern_lock_view_check.isInputEnabled = true

        pattern_lock_ok_check.setOnClickListener {
            if(!pattern_check)
            {
                set_pattern_text_check.text = "패턴을 입력해 주세요"
            }
            else
            {
                removePass()
                savePass()
                startActivity(Intent(applicationContext,MainActivity::class.java))
                finish()
            }
        }

        pattern_lock_cancel_check.setOnClickListener {
            removePreferences()
            startActivity(Intent(applicationContext,PatternLockActivity::class.java))
            finish()
        }

        RxPatternLockView.patternComplete(pattern_lock_view_check)
                .subscribe(object : io.reactivex.functions.Consumer<PatternLockCompleteEvent> {
                    @Throws(Exception::class)
                    override fun accept(patternLockCompleteEvent: PatternLockCompleteEvent) {
                        Log.d(javaClass.name, "Complete: " + patternLockCompleteEvent.pattern!!.toString())
                        set_pattern_text_check.text = "패턴입력이 완료되었습니다."
                    }
                })

        RxPatternLockView.patternChanges(pattern_lock_view_check)
                .subscribe(object : io.reactivex.functions.Consumer<PatternLockCompoundEvent> {
                    override fun accept(event: PatternLockCompoundEvent) {
                        when {
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_STARTED -> {
                                set_pattern_text_check.text = "설정할 패턴을 입력해 주세요."
                                Log.d(javaClass.name, "Pattern drawing started")
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS -> {
                                set_pattern_text_check.text = "패턴을 입력하고 있습니다..."
                                Log.d(javaClass.name, "Pattern progress: " + PatternLockUtils.patternToString(pattern_lock_view_check, event.pattern))
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE -> {
                                Log.d(javaClass.name, "Pattern complete: " + PatternLockUtils.patternToString(pattern_lock_view_check, event.pattern))
                                pattern = PatternLockUtils.patternToString(pattern_lock_view_check, event.pattern)
                                Toast.makeText(applicationContext, pattern, Toast.LENGTH_SHORT).show()
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_CLEARED -> Log.d(javaClass.name, "Pattern has been cleared")
                        }
                    }
                })

    }
}
