package com.example.animalsdatabase.ui.animals

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.preference.PreferenceManager
import com.example.animalsdatabase.AnimalsDatabaseApp
import com.example.animalsdatabase.R
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.ui.BaseFragment
import com.example.animalsdatabase.ui.MainActivity
import com.example.animalsdatabase.ui.animals.ListFragment.Companion.KEY_SORT
import com.example.animalsdatabase.ui.common.ListFragmentViewModel
import com.example.animalsdatabase.ui.common.ListFragmentViewModelFactory
import com.example.animalsdatabase.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_add_item.*
import kotlinx.android.synthetic.main.fragment_add_item.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_animal.view.*
import kotlinx.android.synthetic.main.recycler_view_layout.*
import kotlinx.coroutines.launch

class AddAnimalFragment: BaseFragment(R.layout.fragment_add_item) {
    private val viewModel: ListFragmentViewModel by viewModels {
        ListFragmentViewModelFactory(AnimalsDatabaseApp.INSTANCE.repository, prefs.getString(KEY_SORT, Animal.SORT_BY_CREATED_ASC) ?: "")
    }
    val args: AddAnimalFragmentArgs by navArgs()
    private lateinit var prefs: SharedPreferences

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val context = requireContext()
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        name_edit.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    save.isEnabled = s.isNotBlank() && age_edit.text!!.isNotBlank()
                }

                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
        age_edit.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    save.isEnabled = s.isNotBlank() && name_edit.text!!.isNotBlank()
                }

                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        save.setOnClickListener {
            var animalType: Byte = when(type_spiner.selectedItem.toString()) {
                "Cat" -> Animal.TYPE_CAT
                "Dog" -> Animal.TYPE_DOG
                "Fish" -> Animal.TYPE_FISH
                "Bird" -> Animal.TYPE_BIRD
                "Hamster" -> Animal.TYPE_HUMSTER
                else -> -1
            }
            if(args.addNewAnimal) {
                viewModel.sendAnimal(name_edit.text.toString().trim(), age_edit.text.toString().trim().toInt(),
                    breed_edit.text.toString().trim(), animalType)
            }
            else {
                viewModel.updateAnimal(name_edit.text.toString().trim(), age_edit.text.toString().trim().toInt(),
                    breed_edit.text.toString().trim(), animalType, args.idAnimal)
            }
            (activity as MainActivity).navController.navigate(R.id.action_addAnimalFragment_to_listFragment)
        }
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(!args.addNewAnimal) {
            args.apply {
                name_edit.setText(name)
                age_edit.setText(age)
                breed_edit.setText(breed)
                toolbar.title = toolbarName
                when (type.toByte()) {
                    Animal.TYPE_CAT -> type_spiner.setSelection(0)
                    Animal.TYPE_DOG -> type_spiner.setSelection(1)
                    Animal.TYPE_BIRD -> type_spiner.setSelection(2)
                    Animal.TYPE_FISH -> type_spiner.setSelection(3)
                    Animal.TYPE_HUMSTER -> type_spiner.setSelection(4)
                }
            }
            save.isEnabled = true
        }
    }

}
