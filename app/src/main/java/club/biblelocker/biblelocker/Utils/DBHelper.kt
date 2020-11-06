package club.biblelocker.biblelocker.Utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import club.biblelocker.biblelocker.Models.AlarmDBCtrct
import club.biblelocker.biblelocker.Models.AlarmModel

class DBHelper : SQLiteOpenHelper
{
    val database_version = 1
    val database_name = ""
    val alarm = AlarmDBCtrct()

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(alarm.sqlCreateQuery)
    }

    fun insertAlarm(db: SQLiteDatabase, alarmModel: AlarmModel):Int
    {
        db.beginTransaction()
        try {
            db.execSQL(alarm.sqlInsertQuery + "'${alarmModel.name}', ${alarmModel.time}, ${alarmModel.days}, ${alarmModel.vibrate}, ${alarmModel.repeat}, '${alarmModel.song}');")
            db.setTransactionSuccessful()
            db.endTransaction()
            return getDBSize(db) - 1
        }
        catch (e : Exception)
        {
            Log.wtf("MisakaMOE",e.message)
            db.endTransaction()
        }
        return 0
    }

    fun deleteAlarm(db: SQLiteDatabase, id: Int) : Int
    {
        try {
            val deleteQuery = alarm.sqlDeleteQuery + " where id=$id"
            db.execSQL(deleteQuery)
            return 0
        }
        catch(e:Exception)
        {
            return 1
        }
    }

    fun clearDatabase(db: SQLiteDatabase)
    {
        db.execSQL(alarm.sqlDropQuery)
    }

    fun getAllAlarms(db: SQLiteDatabase) : ArrayList<AlarmModel>
    {
        var alarmModel = AlarmModel(0,0,"","",0,0,"")
        var alarmList = ArrayList<AlarmModel>()
        val cursor = db.rawQuery(alarm.sqlSelectQuery,null)
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                while(cursor.moveToNext())
                {
                    alarmModel = AlarmModel(cursor.getInt(0),cursor.getLong(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6))
                    alarmList.add(alarmModel)
                }
            }
        }
        return alarmList
    }

    fun getAlarm(db: SQLiteDatabase, id:Int) : AlarmModel
    {
        val cursor = db.rawQuery(alarm.sqlSelectQuery,null)
        var alarmModel = AlarmModel(0,0,"","",0,0,"")
        try {
            cursor.move(id)
            alarmModel = AlarmModel(cursor.getInt(0), cursor.getLong(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getString(6))
        }
        catch (e:Exception)
        { Log.d("MisakaMOE", e.message) }
        finally {
            return alarmModel
        }

    }

    fun getDBSize(db: SQLiteDatabase) : Int{
        val cursor = db.rawQuery(alarm.sqlSelectQuery,null)
        return when (cursor != null) {
            true -> cursor.count
            false -> 0
        }
    }

    constructor(context: Context, name : String, version : Int) : super(context, name, null, version)

}