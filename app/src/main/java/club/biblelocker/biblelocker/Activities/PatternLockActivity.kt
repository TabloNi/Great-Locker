package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import club.biblelocker.biblelocker.R
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.andrognito.patternlockview.utils.ResourceUtils
import com.andrognito.rxpatternlockview.RxPatternLockView
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent
import kotlinx.android.synthetic.main.activity_pattern_lock.*
import java.util.function.Consumer

class PatternLockActivity : AppCompatActivity() {

    var pattern = ""

    override fun onBackPressed() {}

    fun savePattern() {
        getSharedPreferences("pattern_pass", Activity.MODE_PRIVATE).edit().putString("pattern_pass", pattern).commit()
    }

    fun removePreferences() {
        getSharedPreferences("pattern_pass", Activity.MODE_PRIVATE).edit().remove("pattern_pass").commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pattern_lock)

        pattern_lock_view.dotCount = 3
        pattern_lock_view.dotNormalSize = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_size) as Int
        pattern_lock_view.dotSelectedSize = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_dot_selected_size) as Int
        pattern_lock_view.pathWidth = ResourceUtils.getDimensionInPx(this, R.dimen.pattern_lock_path_width) as Int
        pattern_lock_view.isAspectRatioEnabled = true
        pattern_lock_view.aspectRatio = PatternLockView.AspectRatio.ASPECT_RATIO_HEIGHT_BIAS
        pattern_lock_view.setViewMode(PatternLockView.PatternViewMode.CORRECT)
        pattern_lock_view.dotAnimationDuration = 150
        pattern_lock_view.pathEndAnimationDuration = 100
        pattern_lock_view.correctStateColor = ResourceUtils.getColor(this, R.color.white)
        pattern_lock_view.isInStealthMode = false;
        pattern_lock_view.isTactileFeedbackEnabled = true;
        pattern_lock_view.isInputEnabled = true
        pattern_lock_view.addPatternLockListener(mPatternLockViewListener)

        pattern_lock_ok.setOnClickListener {
            if (pattern.length < 4) {
                set_pattern_text.text = "적어도 4개 이상의 패턴을 입력해 주세요."
                pattern = ""
            } else {
                removePreferences()
                savePattern()
                startActivity(Intent(this, PatternLockCheckActivity::class.java))
                finish()
            }
        }

        pattern_lock_cancel.setOnClickListener {
            removePreferences()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        RxPatternLockView.patternComplete(pattern_lock_view)
                .subscribe(object : io.reactivex.functions.Consumer<PatternLockCompleteEvent> {
                    @Throws(Exception::class)
                    override fun accept(patternLockCompleteEvent: PatternLockCompleteEvent) {
                        Log.d(javaClass.name, "Complete: " + patternLockCompleteEvent.pattern!!.toString())
                        set_pattern_text.text = "패턴입력이 완료되었습니다."
                    }
                })

        RxPatternLockView.patternChanges(pattern_lock_view)
                .subscribe(object : io.reactivex.functions.Consumer<PatternLockCompoundEvent> {
                    override fun accept(event: PatternLockCompoundEvent) {
                        when {
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_STARTED -> {
                                set_pattern_text.text = "설정할 패턴을 입력해 주세요."
                                Log.d(javaClass.name, "Pattern drawing started")
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS -> {
                                set_pattern_text.text = "패턴을 입력하고 있습니다..."
                                Log.d(javaClass.name, "Pattern progress: " + PatternLockUtils.patternToString(pattern_lock_view, event.pattern))
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE -> {
                                Log.d(javaClass.name, "Pattern complete: " + PatternLockUtils.patternToString(pattern_lock_view, event.pattern))
                                pattern = PatternLockUtils.patternToString(pattern_lock_view, event.pattern)
                                Toast.makeText(applicationContext, pattern, Toast.LENGTH_SHORT).show()
                            }
                            event.eventType == PatternLockCompoundEvent.EventType.PATTERN_CLEARED -> Log.d(javaClass.name, "Pattern has been cleared")
                        }
                    }
                })
    }

    private val mPatternLockViewListener = object : PatternLockViewListener {
        override fun onStarted() {
            Log.d(javaClass.name, "Pattern drawing started")
        }

        override fun onProgress(progressPattern: List<PatternLockView.Dot>) {
            Log.d(javaClass.name, "Pattern progress: " + PatternLockUtils.patternToString(pattern_lock_view, progressPattern))
        }

        override fun onComplete(pattern: List<PatternLockView.Dot>) {
            Log.d(javaClass.name, "Pattern complete: " + PatternLockUtils.patternToString(pattern_lock_view, pattern))
        }

        override fun onCleared() {
            Log.d(javaClass.name, "Pattern has been cleared")
        }
    }

}
