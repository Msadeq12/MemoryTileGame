package com.example.assignment_3.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PlayerDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayer (player : Player)

    @Query("SELECT * FROM player_scores ORDER BY Score DESC")
    fun readAllData(): LiveData<List<Player>>


}