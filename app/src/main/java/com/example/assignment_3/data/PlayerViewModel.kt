package com.example.assignment_3.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* The view mddel is instantiated in the Fragment classes
* It passes on the object towards th data layer with updating data
*/
class PlayerViewModel(application : Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Player>>

    private val repository : PlayerRepository

    init {
        val playerDAO = PlayerDatabase.getDatabase(application).playerDAO()
        repository = PlayerRepository(playerDAO)
        readAllData = repository.readAllData
    }

    // Kotlin coroutine is used here for asynchronous activities
    fun addPlayer(player : Player){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }

    fun updateScore(Name : String, Score: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.UpdateScore(Name, Score)
        }

    }


}