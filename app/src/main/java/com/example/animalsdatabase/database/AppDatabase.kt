package com.example.animalsdatabase.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animalsdatabase.AnimalsDatabaseApp
import com.example.animalsdatabase.database.room.AnimalsDaoRoom
import com.example.animalsdatabase.model.Animal

@Database(
    exportSchema = true,
    entities = [Animal::class],
    version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun animals(): AnimalsDaoRoom

    companion object {
        private const val TAG = "DataBase"
        private const val DB_NAME = "ANIMALSDATABASE0"
        @Volatile
        private var INSTANSE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            Log.d(TAG, "DB getInstance0")
            return INSTANSE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANSE = instance
                Log.d(TAG, "DB getInstance1")
                instance
            }
        }
    }
}