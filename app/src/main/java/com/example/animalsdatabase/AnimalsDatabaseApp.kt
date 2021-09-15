package com.example.animalsdatabase

import android.app.Application
import com.example.animalsdatabase.database.AppDatabase
import com.example.animalsdatabase.repository.AnimalsRepository

class AnimalsDatabaseApp: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repositoryRoom by lazy { AnimalsRepository(database.animals()) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: AnimalsDatabaseApp
            private set
    }
}


