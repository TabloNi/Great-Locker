package club.biblelocker.biblelocker.Models

data class TodayString (
        var content : String,
        var part : String,
        var page : Int?,
        var rows : Int?,
        var len : Int,
        var num : Int
)