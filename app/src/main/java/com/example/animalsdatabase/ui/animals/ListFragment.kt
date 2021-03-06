package com.example.animalsdatabase.ui.animals

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import com.example.animalsdatabase.AnimalsDatabaseApp
import com.example.animalsdatabase.R
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.ui.MainActivity
import com.example.animalsdatabase.ui.common.AnimalClickListener
import com.example.animalsdatabase.ui.common.ListFragmentViewModel
import com.example.animalsdatabase.ui.common.ListFragmentViewModelFactory
import com.example.animalsdatabase.ui.common.RecyclerViewFragment
import com.example.animalsdatabase.ui.common.adapter.AnimalsAdapter
import com.example.animalsdatabase.ui.common.dialogs.AlertDialog
import com.example.animalsdatabase.utils.addDivider
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.recycler_view_layout.*

class ListFragment: RecyclerViewFragment(R.layout.fragment_main), AnimalClickListener, AlertDialog.OnDialogResultListener {
    private val viewModel: ListFragmentViewModel by viewModels {
        ListFragmentViewModelFactory(AnimalsDatabaseApp.INSTANCE.repositoryRoom, prefs.getString(KEY_SORT, Animal.SORT_BY_CREATED_ASC) ?: "" )
    }
    private var currentAnimal: Animal? = null
    private lateinit var prefs: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()
        fab.setOnClickListener {
            var action = ListFragmentDirections.actionListFragmentToAddAnimalFragment("", "", "")
            (activity as MainActivity).navController.navigate(action)
        }
        setting.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_listFragment_to_preferenceSettings)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val currentSorting = prefs.getString(KEY_SORT, Animal.SORT_BY_CREATED_ASC)
        viewModel.changeSort(currentSorting ?: Animal.SORT_BY_CREATED_ASC)
        val adapter = AnimalsAdapter(this,this)
        recyclerView.adapter = adapter
        recyclerView.addDivider()
        viewModel.animals.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onSingleClick(animal: Animal, position: Int) {
        var action = ListFragmentDirections.actionListFragmentToAddAnimalFragment(
            animal.name,
            animal.age.toString(),
            animal.breed,
            animal.type.toInt(),
        "Edit Animal",
            false,
            animal.id)
        (activity as MainActivity).navController.navigate(action)
    }

    override fun onLongClick(animal: Animal, position: Int) {
        currentAnimal = animal
        AlertDialog.show(this, REQUEST_EDIT,
            title = "Delete",
            message = "Delete animal: ${animal.name} ?",
            positiveButton = "Yes",
            negativeButton = "No")
    }

    override fun onDialogResult(requestCode: Int, resultCode: Int, args: Bundle?) {
        if(resultCode == AlertDialog.RESULT_POSITIVE) {
            viewModel.deleteAnimal(currentAnimal!!)
        }
    }

    companion object {
        const val REQUEST_EDIT = 0
        const val KEY_SORT = "sort_type"
    }
}