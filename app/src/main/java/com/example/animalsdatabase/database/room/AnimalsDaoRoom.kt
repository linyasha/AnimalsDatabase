package com.example.animalsdatabase.database.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.animalsdatabase.model.Animal

@Dao
interface AnimalsDaoRoom {

    @Query("SELECT * FROM animals ORDER BY " +
            "CASE WHEN :sort = 'By created(ASC)' THEN createdAt END ASC," +
            "CASE WHEN :sort = 'By age(ASC)' THEN age END ASC," +
            "CASE WHEN :sort = 'By name(ASC)' THEN name END ASC," +
            "CASE WHEN :sort = 'By breed(ASC)' THEN breed END ASC," +
            "CASE WHEN :sort = 'By created(DESC)' THEN createdAt END DESC," +
            "CASE WHEN :sort = 'By age(DESC)' THEN age END DESC," +
            "CASE WHEN :sort = 'By name(DESC)' THEN name END DESC," +
            "CASE WHEN :sort = 'By breed(DESC)' THEN breed END DESC")
    fun getAnimals(sort: String): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal)

    @Query("UPDATE animals SET type = :newType, name = :newName, age = :newAge, breed = :newBreed WHERE id = :id")
    suspend fun edit(id: Int, newType: Byte, newName: String, newAge: Int, newBreed: String)

    @Delete
    suspend fun delete(animal: Animal)
}