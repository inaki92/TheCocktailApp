package com.example.thecocktailsapp.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecocktailsapp.Res.CocktailsRepository
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import com.example.thecocktailsapp.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CocktailsAppViewModel(
    private val cocktailsRepository: CocktailsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    // Expose screen UI state
    private val _cocktail : MutableLiveData<UIState<CocktailsResponse>> = MutableLiveData(UIState.LOADING)
    val cocktail : MutableLiveData<UIState<CocktailsResponse>> get() = _cocktail

    // Handle logic
    init {
        getCocktails()
    }

    private fun getCocktails(){
        viewModelScope.launch(ioDispatcher) {
            cocktailsRepository.getCocktails().collect() {
                _cocktail.postValue(it)
                Log.d(ContentValues.TAG, "TEST: $_cocktail")
            }
        }

    }


}