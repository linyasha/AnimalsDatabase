package com.example.animalsdatabase.repository

import androidx.lifecycle.LiveData
import com.example.animalsdatabase.database.room.AnimalsDaoRoom
import com.example.animalsdatabase.model.Animal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AnimalsRepository(val animalsDao: AnimalsDaoRoom) {
    @Suppress("PropertyName")
    private val TAG = "AnimalsRepository"

    fun getAnimals(sort: String):LiveData<List<Animal>> {
        var animals: LiveData<List<Animal>>? = null
        animals = animalsDao.getAnimals(sort)
        return animals!!
    }

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