package com.example.animalsdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "animals")
data class Animal(
        val type: Byte,
        val name: String,
        val age: Int,
        val breed: String,
        val createdAt: Long = System.currentTimeMillis(),
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0):Parcelable {

    companion object {
               //Can add more
               const val TYPE_CAT: Byte = 0
               const val TYPE_DOG: Byte = 1
               const val TYPE_BIRD: Byte = 2
               const val TYPE_FISH: Byte = 3
               const val TYPE_HUMSTER: Byte = 4
    }

}