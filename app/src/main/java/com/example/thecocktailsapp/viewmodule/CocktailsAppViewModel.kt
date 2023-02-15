package com.example.thecocktailsapp.viewmodule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CocktailsAppViewModel(
    private val cocktailsRepository: CocktailsRepository
): ViewModel() {

    // Expose screen UI state
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val cocktails = ""

    private val _cocktail : MutableLiveData<UIState<CocktailsResponse>> = MutableLiveData(UIState.LOADING)
    val cocktail : MutableLiveData<UIState<CocktailsResponse>> get() = _cocktail


    // Handle logic
    private fun getCocktails(){
        viewModelScope.launch(ioDispatcher) {
            cocktailsRepository.getCocktail(cocktails).collect() {
                Log.d(ContentValues.TAG, "TEST: $cocktails")
                _cocktail.postValue(it)
            }
        }

    }


}