package com.example.thecocktailsapp.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.example.thecocktailsapp.databinding.DrinkViewHolderBinding
import com.example.thecocktailsapp.R
import com.example.thecocktailsapp.databinding.IngredientViewHolderBinding
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.ViewType
import kotlinx.coroutines.NonDisposableHandle.parent

private const val TAG = "CocktailAdapter"

class CocktailAdapter(
    private val itemSet: MutableList<ViewType> = mutableListOf(),
    private val onItemClick: (Drink) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateItems(newItems: List<ViewType>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            CocktailViewHolder(
                DrinkViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            IngredientsViewHolder(
                IngredientViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemSet[position]) {
            is ViewType.DRINK -> 0
            is ViewType.INGREDIENT -> 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = itemSet[position]) {
            is ViewType.DRINK -> (holder as CocktailViewHolder).bind(
                item.drinkList,
                onItemClick
            )
            is ViewType.INGREDIENT -> (holder as IngredientsViewHolder).bind(
                item.ingredientsList,
                item.mesurementList
            )
        }

    }

    override fun getItemCount(): Int = itemSet.size

}

class CocktailViewHolder(
    private val binding: DrinkViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Drink, onItemClick: (Drink) -> Unit) {
        Glide
            .with(binding.root)
            .load(item.drinkThumb)
            .centerCrop()
            .placeholder(R.drawable.baseline_blender_24)
            .error(R.drawable.baseline_broken_image_24)
            .into(binding.ivCocktailPicture)

        binding.tvDrinkName.text = item.drinkName ?: "NO NAME PROVIDED"
        itemView.setOnClickListener { item.let(onItemClick) }
    }


}

class IngredientsViewHolder(
    private val binding: IngredientViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(ingredient: String, measurement: String) {
        binding.tvIngredient.text = ingredient
        binding.tvMeasure.text = measurement
    }
}
