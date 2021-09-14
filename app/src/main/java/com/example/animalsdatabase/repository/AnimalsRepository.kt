package com.example.animalsdatabase.repository

import android.util.Log
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

    fun getAnimals(sort: String):LiveData<List<Animal>> {
        var animals: LiveData<List<Animal>>? = null
        Log.d("FWR", "repository: $sort ")
            when (sort) {
                Animal.SORT_BY_CREATED_ASC -> animals = animalsDao.getAnimals()
                Animal.SORT_BY_CREATED_DESC -> animals = animalsDao.getAnimalsSortedByCreateDESC()
                Animal.SORT_BY_NAME_ASC -> animals = animalsDao.getAnimalsSortedByNameASC()
                Animal.SORT_BY_NAME_DESC -> animals = animalsDao.getAnimalsSortedByNameDESC()
                Animal.SORT_BY_AGE_ASC -> animals = animalsDao.getAnimalsSortedByAgeASC()
                Animal.SORT_BY_AGE_DESC -> animals = animalsDao.getAnimalsSortedByAgeDESC()
                Animal.SORT_BY_BREED_ASC -> animals = animalsDao.getAnimalsSortedByBreedASC()
                Animal.SORT_BY_BREED_DESC -> animals = animalsDao.getAnimalsSortedByBreedDESC()
                else -> animals = animalsDao.getAnimals()
            }
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