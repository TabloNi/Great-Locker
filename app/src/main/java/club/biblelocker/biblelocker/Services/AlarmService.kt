package club.biblelocker.biblelocker.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class AlarmService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this,"Alarm",LENGTH_SHORT).show()

        return START_NOT_STICKY
    }
}
