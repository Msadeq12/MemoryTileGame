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

    // selects top 3 players from player_score database
    @Query("SELECT * FROM player_scores ORDER BY Score DESC LIMIT 3")
    fun readAllData(): LiveData<List<Player>>

    // updates the score for the player during the game, only if the player name is entered
    // at Welcome page
    @Query("UPDATE player_scores SET Score = :Score WHERE Name = :Name")
    suspend fun updateScore(Name : String, Score : Int)


}