package com.example.animalsdatabase.ui.common

import android.util.Log
import androidx.lifecycle.*
import com.example.animalsdatabase.database.AnimalsDao
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.repository.AnimalsRepository
import com.example.animalsdatabase.utils.toLiveData
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val repository: AnimalsRepository, private val defaultSort: String): ViewModel() {

    private val _sort: MutableLiveData<String> by lazy{MutableLiveData(defaultSort)}
    val animals: LiveData<List<Animal>> = _sort.switchMap { sort ->
        repository.getAnimals(sort)
    }

    fun sendAnimal(name: String, age: Int, breed: String?, type:Byte) {
        repository.sendAnimal(Animal(type, name, age, breed ?: ""))
    }

    fun deleteAnimal(animal: Animal) {
        repository.deleteAnimal(animal)
    }

    fun updateAnimal(name: String, age: Int, breed: String?, type:Byte, id: Int) {
        repository.updateAnimal(id, type, name, age, breed ?: "")
    }

    fun changeSort(value: String) {
        _sort.value = value
    }
}

class ListFragmentViewModelFactory(private val repository: AnimalsRepository, private val defaultSort: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListFragmentViewModel(repository, defaultSort) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}