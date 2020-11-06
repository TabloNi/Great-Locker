package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import club.biblelocker.biblelocker.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        motion_lock.setOnClickListener {
            startActivity(Intent(this,MotionLockActivity::class.java))
            finish()
        }

        pattern_lock.setOnClickListener {
            startActivity(Intent(this,PatternLockActivity::class.java))
            finish()
        }

        nowwhat.setOnClickListener {
            Toast.makeText(applicationContext,check_pin(),Toast.LENGTH_SHORT).show()
        }

        pin_lock.setOnClickListener {
            startActivity(Intent(applicationContext,PinLockActivity::class.java))
            finish()
        }
    }

    private fun check_pin() : String {
        return getSharedPreferences("pass_way", Activity.MODE_PRIVATE).getString("pass_way","")
    }
}
