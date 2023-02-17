package com.example.thecocktailsapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecocktailsapp.R
import com.example.thecocktailsapp.databinding.FragmentCommonViewBinding
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.BaseFragment
import com.example.thecocktailsapp.utils.UIState
import com.example.thecocktailsapp.utils.ViewType
import com.example.thecocktailsapp.views.adapter.CocktailAdapter

private const val TAG = "FilterFragment"

class FilterFragment : BaseFragment(), androidx.appcompat.widget.SearchView.OnQueryTextListener {


    private val cocktailAdapter by lazy {
        CocktailAdapter{ item ->
            //Item click
            cocktailViewModel.id = item.idDrink
            findNavController().navigate(R.id.action_FilterFragment_to_DetailsFragment)
        }
    }


    private val binding by lazy {
        FragmentCommonViewBinding.inflate(layoutInflater)
    }

    // todo implement cocktailAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //SearchView
        binding.svSearchCocktail.setOnQueryTextListener(this)


        //RecyclerView
        binding.recyclerCocktails.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )

            adapter = cocktailAdapter

        }

        cocktailViewModel.getCocktailsByAlcohol("Non_Alcoholic")
        getCocktailsNonAlcoholic()
        getCocktailsByName()

        return binding.root
    }


    private fun getCocktailsNonAlcoholic() {
        //ViewModel here
        cocktailViewModel.cocktailByAlcohol.observe(viewLifecycleOwner) { state ->
            val listVT: MutableList<ViewType> = mutableListOf()

            Log.d(TAG, "getCocktailsRandom: Before when")

            when (state) {
                is UIState.LOADING -> {
                    Log.d(TAG, "getCocktailsRandom: Loading")
                }
                is UIState.SUCCESS<List<Drink>> -> {
                    state.response.forEach {
                        Log.d(TAG, "getCocktailsRandom: this is ${it.drinkName}")
                        listVT.add(ViewType.DRINK(it))
                    }
                    cocktailAdapter.updateItems(listVT)
                }
                is UIState.ERROR -> {
                    Log.d(TAG, "getCocktailsRandom: Error")
                    state.error.localizedMessage?.let {
                        showError(it) {


                        }
                    }
                }
            }
        }

    }

    private fun getCocktailsByName() {
        //ViewModel here
        cocktailViewModel.cocktailByName.observe(viewLifecycleOwner) { state ->
            val listVT: MutableList<ViewType> = mutableListOf()

            when (state) {
                is UIState.LOADING -> {
                }
                is UIState.SUCCESS<List<Drink>> -> {
                    state.response.forEach {
                        listVT.add(ViewType.DRINK(it))
                    }
                    cocktailAdapter.updateItems(listVT)
                }
                is UIState.ERROR -> {
                    state.error.localizedMessage?.let {
                        showError(it) {


                        }
                    }
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.svSearchCocktail.getWindowToken(), 0)
    }

    private fun searchByName(query: String) {
        cocktailViewModel.getCocktailsByName(query)
        hideKeyboard()

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByName(query)
            Toast.makeText(requireContext(), "$query", Toast.LENGTH_LONG).show()
        }

        return true

    }

    override fun onQueryTextChange(newText: String?): Boolean = true
}