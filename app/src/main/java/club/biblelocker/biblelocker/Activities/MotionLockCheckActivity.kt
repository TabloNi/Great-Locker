package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import club.biblelocker.biblelocker.R
import club.biblelocker.biblelocker.Utils.Vibrator
import kotlinx.android.synthetic.main.activity_motion_lock_check.*

class MotionLockCheckActivity : AppCompatActivity() {

    var vibrator = Vibrator(applicationContext)
    private var motioncheckresult = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_motion_lock_check)

        motion_one_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("1")
        }

        motion_two_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("2")
        }

        motion_three_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("3")
        }

        motion_four_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("4")
        }

        motion_five_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("5")
        }

        motion_six_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("6")
        }

        motion_seven_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("7")
        }

        motion_eight_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("8")
        }

        motion_nine_check.setOnClickListener {
            vibrator.vibrate(10)
            motioncheckresult.plus("9")
        }

        make_ok_check.setOnClickListener {
            if(getMotion() == motioncheckresult)
            {
                removePass()
                savePass()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
            else
            {
                motioncheckresult = ""
            }
        }

        make_cancel_check.setOnClickListener {
            removePreferences()
            startActivity(Intent(applicationContext,MotionLockActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {}

    private fun getMotion() : String
    {
        return getSharedPreferences("motion_pass", Activity.MODE_PRIVATE).getString("motion_pass","")
    }

    private fun savePass()
    {
        getSharedPreferences("pass_way",Activity.MODE_PRIVATE).edit().putString("pass_way","motion").commit()
    }

    private fun removePass()
    {
        getSharedPreferences("pass_way",Activity.MODE_PRIVATE).edit().remove("pass_way").commit()
    }

    private fun removePreferences()
    {
        getSharedPreferences("motion_pass",Activity.MODE_PRIVATE).edit().remove("motion_pass").commit()
    }
}
