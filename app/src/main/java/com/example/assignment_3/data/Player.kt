package com.example.assignment_3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_scores")
data class Player (
    @PrimaryKey(autoGenerate = true)
    val Id : Int,
    val Name : String,
    val Score : Int
)