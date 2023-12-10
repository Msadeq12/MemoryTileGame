package com.example.assignment_3.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Player>>

    private val repository : PlayerRepository

    init {
        val playerDAO = PlayerDatabase.getDatabase(application).playerDAO()
        repository = PlayerRepository(playerDAO)
        readAllData = repository.readAllData
    }

    fun addPlayer(player : Player){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }


    }


}