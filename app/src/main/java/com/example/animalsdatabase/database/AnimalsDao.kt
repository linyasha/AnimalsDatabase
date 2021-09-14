package com.example.animalsdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.animalsdatabase.model.Animal

@Dao
interface AnimalsDao {
    @Query("SELECT * FROM animals WHERE id = :id")
    fun get(id: Int): Animal

    @Query("SELECT * FROM animals")
    fun getAll(): List<Animal>

    @Query("SELECT * FROM animals ORDER BY createdAt ASC")
    fun getAnimals(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY createdAt DESC")
    fun getAnimalsSortedByCreateDESC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY name ASC")
    fun getAnimalsSortedByNameASC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY name DESC")
    fun getAnimalsSortedByNameDESC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY age ASC")
    fun getAnimalsSortedByAgeASC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY age DESC")
    fun getAnimalsSortedByAgeDESC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY breed ASC")
    fun getAnimalsSortedByBreedASC(): LiveData<List<Animal>>

    @Query("SELECT * FROM animals ORDER BY breed DESC")
    fun getAnimalsSortedByBreedDESC(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animal: Animal)

    @Query("UPDATE animals SET type = :newType, name = :newName, age = :newAge, breed = :newBreed WHERE id = :id")
    fun edit(id: Int, newType: Byte, newName: String, newAge: Int, newBreed: String)

    @Delete
    fun delete(animal: Animal)
}