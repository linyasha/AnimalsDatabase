package com.example.animalsdatabase.repository

import androidx.lifecycle.LiveData
import com.example.animalsdatabase.AnimalsDatabaseApp
import com.example.animalsdatabase.database.AnimalsDao
import com.example.animalsdatabase.database.AppDatabase
import com.example.animalsdatabase.model.Animal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsRepository(val animalsDao: AnimalsDao) {
    @Suppress("PropertyName")
    private val TAG = "AnimalsRepository"

    fun getAnimals():LiveData<List<Animal>> = animalsDao.getAnimals()

    fun sendAnimal(animal:Animal) {
        GlobalScope.launch {
            animalsDao.insert(animal)
        }
    }

    fun deleteAnimal(animal: Animal) {
        GlobalScope.launch {
            animalsDao.delete(animal)
        }
    }

    fun updateAnimal(id: Int, newType: Byte, newName: String, newAge: Int, newBreed: String) {
        GlobalScope.launch {
            animalsDao.edit(id, newType, newName, newAge, newBreed)
        }
    }

}