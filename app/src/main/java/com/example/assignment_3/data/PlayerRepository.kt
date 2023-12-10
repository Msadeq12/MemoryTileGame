package com.example.assignment_3.data

import androidx.lifecycle.LiveData

class PlayerRepository(private val playerDAO : PlayerDAO) {

    val readAllData : LiveData<List<Player>> = playerDAO.readAllData()

    suspend fun addPlayer(player: Player){
        playerDAO.addPlayer(player)
    }
}