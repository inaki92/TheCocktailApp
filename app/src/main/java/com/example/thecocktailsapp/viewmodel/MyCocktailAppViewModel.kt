package com.example.thecocktailsapp.viewmodule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecocktailsapp.Res.CocktailsRepository
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyCocktailsAppViewModel(
    private val cocktailsRepository: CocktailsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    // Expose screen UI state
    var id = ""
    private val _cocktailByAlcohol: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailByAlcohol: MutableLiveData<UIState<List<Drink>>> get() = _cocktailByAlcohol

    private val _cocktailByName: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailByName: MutableLiveData<UIState<List<Drink>>> get() = _cocktailByName

    private val _cocktailById: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailById: MutableLiveData<UIState<List<Drink>>> get() = _cocktailById

    init {
        getCocktailsByAlcohol()
        getCocktailsByName()
        getCocktailsById()
    }

    // Handle logic
    fun getCocktailsByAlcohol(alcohol: String? = null) {
        alcohol?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByName(alcohol).collect {
                    _cocktailById.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

    fun getCocktailsByName(name: String? = null) {
        name?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByName(name).collect {
                    _cocktailById.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

    fun getCocktailsById(id: String? = null) {
        id?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByName(id).collect {
                    _cocktailById.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

}