package com.example.assignment_3.data

import androidx.lifecycle.LiveData

/*
*  The repository class is layer between the viewmodel and the database layer
*  Used for best practice within MVVM architecture
*/
class PlayerRepository(private val playerDAO : PlayerDAO) {

    val readAllData : LiveData<List<Player>> = playerDAO.readAllData()

    suspend fun UpdateScore(Name: String, Score : Int){
        playerDAO.updateScore(Name, Score)
    }

    suspend fun addPlayer(player: Player){
        playerDAO.addPlayer(player)
    }
}