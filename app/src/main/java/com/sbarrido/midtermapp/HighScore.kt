package com.sbarrido.midtermapp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class HighScore(
    @PrimaryKey(autoGenerate = true)
    var scoreID: Long = 0L,
    @ColumnInfo(name = "player_name")
    var pname: String = "",
    @ColumnInfo(name = "score")
    var scoreGuess: Int = 0
)
