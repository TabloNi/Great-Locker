package club.biblelocker.biblelocker.Models

class AlarmDBCtrct
{
    val table = "ALARM"
    val id = "ID"
    val time="TIME"
    val name = "NAME"
    val days = "DAYS"
    val vibrate = "VIBRATE"
    val repeat = "REPEAT"
    val song = "SONG"

    val sqlCreateQuery = "CREATE TABLE IF NOT EXISTS $table($id INTEGER PRIMARY KEY AUTOINCREMENT, $time INTEGER NOT NULL, $name TEXT NOT NULL, $days TEXT NOT NULL, $vibrate INTEGER NOT NULL, $repeat INTEGER NOT NULL, $song TEXT NOT NULL);"

    val sqlDropQuery = "DROP TABLE IF EXISTS $table"

    val sqlSelectQuery = "SELECT * FROM $table"

    val sqlInsertQuery = "INSERT OR REPLACE INTO $table ($name, $time, $days, $vibrate, $repeat, $song) VALUES ("

    val sqlDeleteQuery = "DELETE FROM $table"
}