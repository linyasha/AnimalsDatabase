package com.example.animalsdatabase.ui.common

import androidx.lifecycle.*
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.repository.AnimalsRepository

class ListFragmentViewModel(private val repositoryRoom: AnimalsRepository, private val defaultSort: String): ViewModel() {


    private val _sort: MutableLiveData<String> by lazy{MutableLiveData(defaultSort)}
    val animals: LiveData<List<Animal>> = _sort.switchMap { sort ->
        repositoryRoom.getAnimals(sort)
    }

    fun sendAnimal(name: String, age: Int, breed: String?, type:Byte) {
        repositoryRoom.sendAnimal(Animal(type, name, age, breed ?: ""))
    }

    fun deleteAnimal(animal: Animal) {
        repositoryRoom.deleteAnimal(animal)
    }

    fun updateAnimal(name: String, age: Int, breed: String?, type:Byte, id: Int) {
        repositoryRoom.updateAnimal(id, type, name, age, breed ?: "")
    }

    fun changeSort(value: String) {
        _sort.value = value
    }
}

class ListFragmentViewModelFactory(private val repositoryRoom: AnimalsRepository, private val defaultSort: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListFragmentViewModel(repositoryRoom, defaultSort) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}