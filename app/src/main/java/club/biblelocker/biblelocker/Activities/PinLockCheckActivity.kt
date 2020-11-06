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
import kotlinx.android.synthetic.main.activity_pin_lock_check.*

class PinLockCheckActivity : AppCompatActivity() {

    var pin = ""
    var isSeeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_pin_lock_check)
        val vibrator = Vibrator(applicationContext)
        pin_one_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("1")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_two_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("2")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_three_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("3")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_four_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("4")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_five_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("5")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_six_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("6")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_seven_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("7")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_eight_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("8")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_nine_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("9")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_zero_check.setOnClickListener {
            vibrator.vibrate(10)
            pin.plus("0")
            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_backspace_check.setOnClickListener {
            vibrator.vibrate(10)
            if(pin.isNotEmpty())
            {
                pin.dropLast(1)
                if(isSeeMode)
                {
                    pin_text_check.text = pin
                }
                else
                {
                    pin_text_check.text = pin
                    pin_text_check.text.replace(Regex("[0-9]"), "*")
                }
            }
        }

        pin_see_check.setOnClickListener {
            vibrator.vibrate(10)
            if(isSeeMode)
            {
                isSeeMode = false
                pin_see_check.setImageResource(R.drawable.eye_on)
            }
            else
            {
                isSeeMode = true
                pin_see_check.setImageResource(R.drawable.eye_off)
            }

            if(isSeeMode)
            {
                pin_text_check.text = pin
            }
            else
            {
                pin_text_check.text = pin
                pin_text_check.text.replace(Regex("[0-9]"), "*")
            }
        }

        pin_make_ok_check.setOnClickListener {
            if(pin.length < 4)
                Toast.makeText(applicationContext,"적어도 4개 이상의 비밀번호를 입력해 주세요",Toast.LENGTH_SHORT).show()
            else
            {
                if(check_pinpass() == pin){
                    Toast.makeText(applicationContext, "눼 마다용", Toast.LENGTH_SHORT).show()
                    delete_passway()
                    save_pin_passway()
                    finish()
                }
                else
                {
                    Toast.makeText(applicationContext, "X", Toast.LENGTH_SHORT).show()

                }
            }
        }

        pin_make_cancel_check.setOnClickListener {
            removePreferences()
            startActivity(Intent(applicationContext,MainActivity::class.java))
            finish()
        }


    }

    private fun check_pinpass(): String {
        val pref = getSharedPreferences("pin_pass", Activity.MODE_PRIVATE)
        val motion_pass_check = pref.getString("pin_pass", "")
        return motion_pass_check
    }

    private fun save_pin_passway() {


        val pref = getSharedPreferences("pass_way", Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("pass_way", "pin")
        editor.commit()

    }


    private fun delete_passway() {
        val pref = getSharedPreferences("pass_way", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove("pass_way")
        editor.commit()

    }

    private fun removePreferences() {
        val pref = getSharedPreferences("pinn_pass", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove("pin_pass")
        editor.commit()
    }
}
