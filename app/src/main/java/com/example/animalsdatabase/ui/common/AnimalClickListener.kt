package com.example.animalsdatabase.ui.common

import com.example.animalsdatabase.model.Animal

interface AnimalClickListener {
    fun onSingleClick(animal: Animal, position: Int)
    fun onLongClick(animal: Animal, position: Int)
}