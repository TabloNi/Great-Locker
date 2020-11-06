package club.biblelocker.biblelocker.Models

/**
 * Created by Sunrin on 2018-04-10.
 */
class AlarmModel(val id:Int,
                 val time:Long,
                 val name:String,
                 val days:String,
                 val vibrate : Int,
                 val repeat : Int,
                 val song : String)