package com.example.animalsdatabase.database.cursor

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.room.Dao
import com.example.animalsdatabase.database.room.AnimalsDaoRoom
import com.example.animalsdatabase.model.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalsDaoCursor private constructor(db: AnimalsCursorDatabase) : AnimalsDaoRoom {

    private val writableDatabase = db.writableDatabase
    private val readableDatabase = db.readableDatabase

    private suspend fun getAll(sort: String):List<Animal>{
        return withContext(Dispatchers.IO) {
            val listOfCars = mutableListOf<Animal>()
            val db = writableDatabase
            val selectQuery =
                when (sort) {
                    Animal.SORT_BY_CREATED_ASC -> "SELECT * FROM animals ORDER BY createdAt ASC"
                    Animal.SORT_BY_CREATED_DESC -> "SELECT * FROM animals ORDER BY createdAt DESC"
                    Animal.SORT_BY_NAME_ASC -> "SELECT * FROM animals ORDER BY name ASC"
                    Animal.SORT_BY_NAME_DESC -> "SELECT * FROM animals ORDER BY name DESC"
                    Animal.SORT_BY_BREED_ASC -> "SELECT * FROM animals ORDER BY breed ASC"
                    Animal.SORT_BY_BREED_DESC -> "SELECT * FROM animals ORDER BY breed DESC"
                    Animal.SORT_BY_AGE_ASC -> "SELECT * FROM animals ORDER BY age ASC"
                    Animal.SORT_BY_AGE_DESC -> "SELECT * FROM animals ORDER BY age DESC"
                    else -> "SELECT * FROM animals"
                }
            val cursor = db.rawQuery(selectQuery,null)
            cursor?.let{
                if (cursor.moveToFirst()) {
                    do {
                        val type = cursor.getInt(cursor.getColumnIndexOrThrow("type"))
                        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                        val age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
                        val breed = cursor.getString(cursor.getColumnIndexOrThrow("breed"))
                        val createdAt = cursor.getLong(cursor.getColumnIndexOrThrow("createdAt"))
                        val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))

                        listOfCars.add(Animal(type.toByte(), name, age, breed, createdAt, id))
                    } while (cursor.moveToNext())
                }
            }
            cursor.close()
            listOfCars
        }
    }

    override fun getAnimals(sort: String): LiveData<List<Animal>> {
        return liveData {
            emit(getAll(sort))
        }
    }

    override suspend fun insert(animal: Animal) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TYPE,animal.type)
        values.put(NAME,animal.name)
        values.put(AGE,animal.age)
        values.put(BREED,animal.breed)
        values.put(CREATEDAT,animal.createdAt)
        values.put(ID,animal.id)
        db.insert(DB_NAME, null, values)
        db.close()
    }

    override suspend fun edit(id: Int, newType: Byte, newName: String, newAge: Int, newBreed: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(TYPE, newType)
        values.put(NAME, newName)
        values.put(AGE, newAge)
        values.put(BREED, newBreed)
        db.update(DB_NAME, values, "$ID=?", arrayOf(id.toString()))
        db.close()
    }

    override suspend fun delete(animal: Animal) {
        val db = writableDatabase
        db.delete("animals","$ID=?", arrayOf(animal.id.toString()))
        db.close()
    }

    companion object {
        const val DB_NAME = "ANIMALSDATABASE0"
        const val TYPE = "type"
        const val NAME = "name"
        const val AGE = "age"
        const val BREED = "breed"
        const val CREATEDAT = "createdAt"
        const val ID = "id"

        private var instance: AnimalsDaoCursor? = null

        fun create(db: AnimalsCursorDatabase): AnimalsDaoCursor {
            if (instance == null) {
                instance = AnimalsDaoCursor(db)
            }
            return instance!!
        }
    }
}
