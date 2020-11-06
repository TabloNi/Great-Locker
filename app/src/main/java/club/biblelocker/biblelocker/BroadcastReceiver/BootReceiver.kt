package club.biblelocker.biblelocker.BroadcastReceiver

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import club.biblelocker.biblelocker.Utils.AlarmController

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val alarms = AlarmController().getAllAlarms(context)
        val alarmManager : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d("MisakaMOE","Boot")
        for(alarm in alarms)
        {
            Log.d("MisakaMOE", "alarm exist")
            alarmManager.set(
                    RTC_WAKEUP,
                    alarm.time,
                    pendingIntent
            )
        }

    }
}
