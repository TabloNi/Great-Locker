package club.biblelocker.biblelocker.BroadcastReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import club.biblelocker.biblelocker.Activities.TestActivity
import club.biblelocker.biblelocker.Services.AlarmService

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"Alarm",LENGTH_SHORT).show()
        Log.d("MisakaMOE","Receiver Enter")

        val intent = Intent(context,AlarmService::class.java)
        context.startService(intent)
    }
}
