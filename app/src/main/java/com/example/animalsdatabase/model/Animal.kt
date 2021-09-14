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

                const val SORT_BY_NAME_ASC = "By name(ASC)"
                const val SORT_BY_NAME_DESC = "By name(DESC)"
                const val SORT_BY_AGE_ASC = "By age(ASC)"
                const val SORT_BY_AGE_DESC = "By age(DESC)"
                const val SORT_BY_BREED_ASC = "By breed(ASC)"
                const val SORT_BY_BREED_DESC = "By breed(DESC)"
                const val SORT_BY_CREATED_ASC = "By created(ASC)"
                const val SORT_BY_CREATED_DESC = "By created(DESC)"
    }

}