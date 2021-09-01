package com.example.animalsdatabase.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalsdatabase.database.AnimalsDao
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.repository.AnimalsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val repository: AnimalsRepository): ViewModel() {
    val animals: LiveData<List<Animal>> = repository.getAnimals()

    fun sendAnimal(name: String, age: Int, breed: String?, type:Byte) {
        repository.sendAnimal(Animal(type, name, age, breed ?: ""))
    }

    fun deleteAnimal(animal: Animal) {
        repository.deleteAnimal(animal)
    }

    fun updateAnimal(name: String, age: Int, breed: String?, type:Byte, id: Int) {
        repository.updateAnimal(id, type, name, age, breed ?: "")
    }

}

class ListFragmentViewModelFactory(private val repository: AnimalsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}