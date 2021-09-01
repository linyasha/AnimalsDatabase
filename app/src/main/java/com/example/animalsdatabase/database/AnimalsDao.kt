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

    @Query("SELECT * FROM animals")
    fun getAnimals(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animal: Animal)

    @Query("UPDATE animals SET type = :newType, name = :newName, age = :newAge, breed = :newBreed WHERE id = :id")
    fun edit(id: Int, newType: Byte, newName: String, newAge: Int, newBreed: String)

    @Delete
    fun delete(animal: Animal)
}