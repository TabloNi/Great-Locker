package club.biblelocker.biblelocker.Utils

class AlarmParser {
    fun parseDays(days : String) : IntArray
    {
        val array = IntArray(7)
        for (i in 0..6)
        {
            array[i] = days[i].toInt()
        }
        return array
    }


}