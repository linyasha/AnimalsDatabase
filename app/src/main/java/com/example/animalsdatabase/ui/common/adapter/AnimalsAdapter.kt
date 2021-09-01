package com.example.animalsdatabase.ui.common.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.example.animalsdatabase.R
import com.example.animalsdatabase.model.Animal
import com.example.animalsdatabase.ui.common.AnimalClickListener
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalsAdapter(
    private val fragment: Fragment,
    private val clickListener: AnimalClickListener
) : BaseListAdapter<Animal>(object: DiffUtil.ItemCallback<Animal>() {

    override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
        return oldItem.type == newItem.type &&
                oldItem.name == newItem.name &&
                oldItem.age == newItem.age &&
                oldItem.breed == newItem.breed &&
                oldItem.createdAt == newItem.createdAt
    }
}) {

    override val layoutId: Int = R.layout.item_animal

    override fun bind(item: Animal, view: View, position: Int) {
        with(view) {
            when(item.type) {
                Animal.TYPE_CAT -> {picture.setBackgroundResource(R.drawable.icon_cat)}
                Animal.TYPE_DOG -> {picture.setBackgroundResource(R.drawable.icon_dog)}
                Animal.TYPE_FISH -> {picture.setBackgroundResource(R.drawable.icon_fish)}
                Animal.TYPE_HUMSTER -> {picture.setBackgroundResource(R.drawable.icon_humster)}
                Animal.TYPE_BIRD -> {picture.setBackgroundResource(R.drawable.icon_bird)}

            }
            name.text = item.name
            breed.text = item.breed
            age.text = item.age.toString()
            setOnClickListener { clickListener.onSingleClick(item, position)}
            setOnLongClickListener { clickListener.onLongClick(item, position); true }
        }
    }
}