package com.example.thecocktailsapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecocktailsapp.R
import com.example.thecocktailsapp.databinding.FragmentCommonViewBinding
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.model.domain.mapToDrink
import com.example.thecocktailsapp.model.domain.mapToDrinkFromTable
import com.example.thecocktailsapp.utils.BaseFragment
import com.example.thecocktailsapp.utils.UIState
import com.example.thecocktailsapp.utils.ViewType
import com.example.thecocktailsapp.views.adapter.CocktailAdapter
import com.example.thecocktailsapp.database.DrinkTable

private const val TAG = "FavoriteFragment"

class FavoriteFragment : BaseFragment() {


    private val binding by lazy {
        FragmentCommonViewBinding.inflate(layoutInflater)
    }

    private val cocktailAdapter by lazy {
        CocktailAdapter{item ->
            cocktailViewModel.id = item.idDrink
            findNavController().navigate(R.id.action_FavoriteFragment_to_DetailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.recyclerCocktails.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
            adapter = cocktailAdapter
        }

        cocktailViewModel.getFavoriteCocktails()
        getFavoriteCocktails()

        return binding.root
    }

    private fun getFavoriteCocktails(){
        cocktailViewModel.favoriteCocktails.observe(viewLifecycleOwner){ state ->
            val listVT: MutableList<ViewType> = mutableListOf()
            when(state){
                is UIState.LOADING -> {}
                is UIState.SUCCESS<List<DrinkTable>> -> {
                    val drinkList = state.response.mapToDrinkFromTable()
                    drinkList.forEach {
                        listVT.add(ViewType.DRINK(it))
                    }
                    cocktailAdapter.updateItems(listVT)
                }
                is UIState.ERROR -> {
                    Log.d(TAG, "getCocktailsFavorite: Error")
                    state.error.localizedMessage?.let {
                        showError(it) {
                        }
                    }
                }
            }
        }
    }
}