package club.biblelocker.biblelocker.Utils

import android.content.Context
import android.util.Log
import club.biblelocker.biblelocker.R
import club.biblelocker.biblelocker.Models.TodayString
import org.json.JSONArray
import java.io.InputStreamReader
import java.util.*

class JsonParser {

    fun getBuddaString(context : Context, length: Int) : TodayString
    {
        val returnValue = TodayString("", "", 0, 0, 0, 1)
        try {
            val jArray = JSONArray(InputStreamReader(context.resources.openRawResource(R.raw.budda)).readText()).getJSONObject(length).getJSONArray("sentences")
            val jObject = jArray.getJSONObject(Random().nextInt(jArray.length())) //Short index
            returnValue.content = jObject.getString("line")
            returnValue.part = jObject.getString("part")
            returnValue.num = jObject.getInt("num")
            returnValue.len = jObject.getInt("len")
        } catch (e: Exception) {
            Log.d("MisakaMOE", e.message)
        }
        return returnValue
    }

    fun getNewBibleString(context: Context, length: Int): TodayString {
        var returnValue = getBibleString(context,0,length)
        return returnValue
    }

    fun getOldBibleString(context: Context, length : Int): TodayString {
        var returnValue = getBibleString(context,1,length)
        return returnValue
    }

    fun getBibleString(context: Context, time : Int, length: Int): TodayString {
        val returnValue = TodayString("", "", 0, 0, 0, 1)

        try {
            val jArray = JSONArray(InputStreamReader(context.resources.openRawResource(R.raw.bible)).readText()).getJSONObject(time).getJSONArray("sentences").getJSONObject(length).getJSONArray("dex")
            val jObject = jArray.getJSONObject(Random().nextInt(jArray.length())) //Short index
            returnValue.content = jObject.getString("line")
            returnValue.part = jObject.getString("part")
            returnValue.num = jObject.getInt("num")
            returnValue.len = jObject.getInt("len")
            returnValue.page = jObject.getInt("page")
            returnValue.rows = jObject.getInt("rows")
        } catch (e: Exception) {
            Log.d("MisakaMOE", e.message)
        }
        return returnValue
    }

}