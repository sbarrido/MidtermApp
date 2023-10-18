package com.sbarrido.midtermapp
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ScoreDAO {
    @Insert
    suspend fun insert(score: HighScore)
    @Update
    suspend fun update(score: HighScore)
    @Delete
    suspend fun delete(score: HighScore)
    @Query("SELECT * FROM score_table ORDER BY score DESC")
    fun getAll(): LiveData<List<HighScore>>
}