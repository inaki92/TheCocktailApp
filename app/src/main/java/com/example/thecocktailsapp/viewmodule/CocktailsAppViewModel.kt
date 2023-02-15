package com.example.thecocktailsapp.viewmodule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CocktailsAppViewModel(): ViewModel() {

    // Expose screen UI state
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _cocktail : MutableLiveData<UIState<CocktailsResponse>> = MutableLiveData(UIState.LOADING)
    val cocktail : MutableLiveData<UIState<CocktailsResponse>> get() = _cocktail


    // Handle logic
    private fun getCocktails(){



    }


}