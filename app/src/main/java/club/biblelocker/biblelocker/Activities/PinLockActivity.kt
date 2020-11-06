package club.biblelocker.biblelocker.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import club.biblelocker.biblelocker.R
import club.biblelocker.biblelocker.Utils.Vibrator
import kotlinx.android.synthetic.main.activity_pin_lock.*

class PinLockActivity : AppCompatActivity() {

    var pin = ""
    var isSeeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pin_lock)
        val vibrator = Vibrator(applicationContext)
        pin_one.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("1")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_two.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("2")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_three.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("3")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_four.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("4")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_five.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("5")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_six.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("6")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_seven.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("7")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_eight.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("8")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_nine.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("9")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_zero.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("0")
            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_backspace.setOnClickListener {
            vibrator.vibrate(10)
            if(pin.isNotEmpty())
            {
                pin.dropLast(1)
                if(isSeeMode)
                {
                    pin_text.text = pin
                }
                else
                {
                    pin_text.text = pin
                    pin_text.text.replace(Regex("[0-9]"), "*")
                }
            }
        }

        pin_see.setOnClickListener {
            vibrator.vibrate(10)
            if(isSeeMode)
            {
                isSeeMode = false
                pin_see.setImageResource(R.drawable.eye_on)
            }
            else
            {
                isSeeMode = true
                pin_see.setImageResource(R.drawable.eye_off)
            }

            if(isSeeMode)
            {
                pin_text.text = pin
            }
            else
            {
                pin_text.text = pin
                pin_text.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_make_ok.setOnClickListener {
            if(pin.length < 4)
                Toast.makeText(applicationContext,"적어도 4개 이상의 비밀번호를 입력해 주세요",Toast.LENGTH_SHORT).show()
            else
            {
                removePreferences()
                save_motionpass()
                startActivity(Intent(applicationContext,PinLockCheckActivity::class.java))
                finish()
            }
        }

        pin_make_cancel.setOnClickListener {
            removePreferences()
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }

    }

    private fun save_motionpass() {
        val pref = getSharedPreferences("pin_pass", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("pin_pass", pin)
        editor.commit()


    }

    private fun removePreferences() {
        val pref = getSharedPreferences("pin_pass", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove("pin_pass")
        editor.commit()
    }

}
