package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import club.biblelocker.biblelocker.R
import club.biblelocker.biblelocker.Utils.Vibrator
import kotlinx.android.synthetic.main.activity_motion_lock.*

class MotionLockActivity : AppCompatActivity() {

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun save_motion()
    {
        val s = getSharedPreferences("motion_pass", Activity.MODE_PRIVATE)
        val editor = s.edit()
        editor.putString("motion_pass",motionresult)
        editor.commit()
    }

    private fun remove_motion()
    {
        val s = getSharedPreferences("motion_pass", Activity.MODE_PRIVATE)
        val editor = s.edit()
        editor.remove("motion_pass")
        editor.commit()
    }


    private var motionresult = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_motion_lock)

        val vibrator = Vibrator(applicationContext)

        motion_one.setOnClickListener{
            vibrator.vibrate(10)
            motion_one.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("1")
        }

        motion_two.setOnClickListener {
            vibrator.vibrate(10)
            motion_two.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("2")
        }

        motion_three.setOnClickListener {
            vibrator.vibrate(10)
            motion_three.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("3")
        }

        motion_four.setOnClickListener {
            vibrator.vibrate(10)
            motion_four.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("4")
        }

        motion_five.setOnClickListener {
            vibrator.vibrate(10)
            motion_five.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("5")
        }

        motion_six.setOnClickListener {
            vibrator.vibrate(10)
            motion_six.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("6")
        }

        motion_seven.setOnClickListener {
            vibrator.vibrate(10)
            motion_seven.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("7")
        }

        motion_eight.setOnClickListener{
            vibrator.vibrate(10)
            motion_eight.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("8")
        }

        motion_nine.setOnClickListener {
            vibrator.vibrate(10)
            motion_nine.setBackgroundResource(R.drawable.motion_lock_outline_light)
            motionresult.plus("9")
        }

        make_ok.setOnClickListener {
            if(motionresult.length < 4) {
                Toast.makeText(this, "적어도 4개 이상의 모션을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                motionresult = ""
            }
            else {
                remove_motion()
                save_motion()
                Toast.makeText(applicationContext,motionresult,Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext,MotionLockCheckActivity::class.java))
            }
        }

        make_cancel.setOnClickListener {
            remove_motion()
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }

    }
}
