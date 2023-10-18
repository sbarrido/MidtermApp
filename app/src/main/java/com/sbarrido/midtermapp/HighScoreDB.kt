package com.sbarrido.midtermapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HighScore::class], version = 1, exportSchema = false)
abstract class HighScoreDB : RoomDatabase() {
    abstract val scoreDao : ScoreDAO
    companion object {
        @Volatile
        private var INSTANCE: HighScoreDB? = null
        fun getInstance(context: Context): HighScoreDB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HighScoreDB::class.java,
                        "score_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}