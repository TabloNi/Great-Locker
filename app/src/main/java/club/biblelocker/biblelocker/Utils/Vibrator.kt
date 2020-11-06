package club.biblelocker.biblelocker.Utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect

class Vibrator(context: Context) {
    var vibrator: android.os.Vibrator? = null
    fun vibrate(millisecond: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            vibrator!!.vibrate(VibrationEffect.createOneShot(millisecond,1000))
        else
            vibrator!!.vibrate(millisecond)
    }

    init {
        vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator
    }
}