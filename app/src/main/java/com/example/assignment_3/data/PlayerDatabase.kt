package com.example.assignment_3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDatabase : RoomDatabase() {

    abstract fun playerDAO() : PlayerDAO

    companion object {

        @Volatile
        private var INSTANCE : PlayerDatabase? = null

        fun getDatabase(context: Context) : PlayerDatabase{
            val instance = INSTANCE

            if(instance != null){
                return instance
            }

            synchronized(this){
                val insta = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerDatabase::class.java,
                    "player_database"
                ).build()
                INSTANCE = insta

                return insta
            }
        }
    }
}