package com.example.thecocktailsapp.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.thecocktailsapp.R
import com.example.thecocktailsapp.databinding.CocktailsDetailsFragmentBinding
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailResponse
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.BaseFragment
import com.example.thecocktailsapp.utils.UIState
import com.example.thecocktailsapp.utils.ViewType
import com.example.thecocktailsapp.views.adapter.CocktailAdapter


class DetailsFragment : BaseFragment() {

    private lateinit var currentCocktail: CocktailResponse

    private val cocktailAdapter by lazy {
        CocktailAdapter(mutableListOf(),
            {
            },
            { star ->


            })
    }

    private val binding by lazy {
        CocktailsDetailsFragmentBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //RecyclerView
        binding.rvIngredientsList.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = cocktailAdapter

        }

        val id = cocktailViewModel.id

        cocktailViewModel.getCocktailsById(id)

        cocktailViewModel.cocktailById.observe(viewLifecycleOwner) {
            val listVT: MutableList<ViewType> = mutableListOf()

            when (it) {
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS<List<Drink>> -> {
                    val currntDrink = it.response[0]
                    currntDrink.ingredients.forEachIndexed { index, s ->
                        listVT.add(ViewType.INGREDIENT(s, currntDrink.measures[index]))
                    }

                    binding.tvDrinkName.text = currntDrink.drinkName
                    binding.tvInstructions.text = currntDrink.instructions

                    Glide
                        .with(binding.root)
                        .load(currntDrink.drinkThumb)
                        .centerCrop()
                        .placeholder(R.drawable.baseline_blender_24)
                        .error(R.drawable.baseline_blender_24)
                        .into(binding.ivCocktailPicture)

                    cocktailAdapter.updateItems(listVT)

                }
                is UIState.ERROR -> {

                    it.error.localizedMessage?.let {
                        showError(it) {
                        }
                    }
                }
            }

        }

        // Inflate the layout for this fragment

        return binding.root
    }
}