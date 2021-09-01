package com.example.animalsdatabase

import android.app.Application
import android.util.Log
import com.example.animalsdatabase.database.AppDatabase
import com.example.animalsdatabase.repository.AnimalsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.math.log

class AnimalsDatabaseApp: Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { AnimalsRepository(database.animals()) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: AnimalsDatabaseApp
            private set
    }
}


