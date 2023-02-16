package com.example.thecocktailsapp.viewmodule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CocktailsAppViewModel(
    private val cocktailsRepository: CocktailsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    // Expose screen UI state


    private val _cocktail : MutableLiveData<UIState<CocktailsResponse>> = MutableLiveData(UIState.LOADING)
    val cocktail : MutableLiveData<UIState<CocktailsResponse>> get() = _cocktail


    // Handle logic
    private fun getCocktails(){
        viewModelScope.launch(ioDispatcher) {
            cocktailsRepository.getCocktail().collect() {
                _cocktail.postValue(it)
                Log.d(ContentValues.TAG, "TEST: $_cocktail")
            }
        }

    }


}