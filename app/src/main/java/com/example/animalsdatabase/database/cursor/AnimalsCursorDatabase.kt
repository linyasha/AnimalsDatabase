package com.example.animalsdatabase.database.cursor

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AnimalsCursorDatabase private constructor(context: Context):
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    val movieDao = AnimalsDaoCursor.create(this)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ${DB_NAME} (" +
                "${AnimalsDaoCursor.TYPE}	INT NOT NULL," +
                "${AnimalsDaoCursor.NAME}	TEXT NOT NULL," +
                "${AnimalsDaoCursor.AGE}	INTEGER NOT NULL," +
                "${AnimalsDaoCursor.BREED}	TEXT NOT NULL," +
                "${AnimalsDaoCursor.CREATEDAT}	INTEGER NOT NULL," +
                "${AnimalsDaoCursor.ID}	INTEGER NOT NULL," +
                "PRIMARY KEY(${AnimalsDaoCursor.ID} AUTOINCREMENT)" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        const val DB_NAME = "ANIMALSDATABASE0"
        const val DB_VERSION = 1

        private var instance: AnimalsCursorDatabase? = null

        fun create(context: Context): AnimalsCursorDatabase {
            if (instance == null) {
                instance = AnimalsCursorDatabase(context)
            }
            return instance!!
        }
    }
}