package club.biblelocker.biblelocker.Utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import club.biblelocker.biblelocker.BroadcastReceiver.AlarmReceiver
import club.biblelocker.biblelocker.Models.AlarmModel
import java.util.*
import kotlin.collections.ArrayList

class AlarmController {


    /*
        Adds an alarm to AlarmManager and DB.

        @param [context] : application context.
        @param [calendar] : alarm calendar.

        @return the Index of Alarm
    */

    fun addAlarm(context: Context, alarmModel: AlarmModel) : Int
    {
        val db = DBHelper(context, "alarm.db",1)
        val calendar : Calendar = Calendar.getInstance()
        if(calendar.timeInMillis < alarmModel.time) //현재 시각 이후임 : 그냥 알려줘도 됨
        {

        }
        else //이미 지났음 -> 내일 알려줘야함
        {

        }
        //
        //
        //
        //

        return db.insertAlarm(db.writableDatabase,alarmModel)

    }

    /*
        Remove an alarm from DB and alarmManager.

        @param [context] : application context.
        @param [int] : index of alarm

        @return successful? or not?
     */

    fun removeAlarm(context: Context, int: Int)
    {
        val db = DBHelper(context, "alarm.db",1)
        db.deleteAlarm(db.writableDatabase,int)
        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager).cancel(PendingIntent.getBroadcast(context,int, Intent(context, AlarmReceiver::class.java),PendingIntent.FLAG_UPDATE_CURRENT))
    }

    fun getAllAlarms(context: Context) : ArrayList<AlarmModel>
    {
        val db = DBHelper(context, "alarm.db",1)
        return db.getAllAlarms(db.writableDatabase)
    }

    fun getLastId(context: Context) : Int
    {
        val db = DBHelper(context, "alarm.db",1)
        return db.getDBSize(db.writableDatabase)
    }

}