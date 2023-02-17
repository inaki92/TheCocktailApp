package com.example.thecocktailsapp.viewmodule

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thecocktailsapp.Res.CocktailsRepository
import com.example.thecocktailsapp.database.DrinkTable
import com.example.thecocktailsapp.database.LocalRepository
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MyCocktailAppViewModel"
@HiltViewModel
class MyCocktailsAppViewModel @Inject constructor(
    private val cocktailsRepository: CocktailsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val localRepository: LocalRepository
) : ViewModel() {

    // Expose screen UI state
    var id = ""
    private val _cocktailByAlcohol: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailByAlcohol: LiveData<UIState<List<Drink>>> get() = _cocktailByAlcohol

    private val _cocktailByName: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailByName: LiveData<UIState<List<Drink>>> get() = _cocktailByName

    private val _cocktailById: MutableLiveData<UIState<List<Drink>>> =
        MutableLiveData(UIState.LOADING)
    val cocktailById: LiveData<UIState<List<Drink>>> get() = _cocktailById

    private val _favoriteCocktails: MutableLiveData<UIState<List<DrinkTable>>> =
        MutableLiveData(UIState.LOADING)
    val favoriteCocktails: LiveData<UIState<List<DrinkTable>>> get() = _favoriteCocktails

    init {
        getCocktailsByAlcohol()
        getCocktailsByName()
        getCocktailsById()
    }

    // Handle logic
    fun getCocktailsByAlcohol(alcohol: String? = null) {
        alcohol?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByAlcohol(alcohol).collect {
                    _cocktailByAlcohol.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

    fun getCocktailsByName(name: String? = null) {
        name?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByName(name).collect {
                    _cocktailByName.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

    fun getCocktailsById(id: String? = null) {
        id?.let {
            viewModelScope.launch(ioDispatcher) {
                cocktailsRepository.getCocktailsByID(id).collect {
                    _cocktailById.postValue(it)
                    Log.d(ContentValues.TAG, "TEST: $_cocktailById")
                }
            }
        }
    }

    fun updateFavoriteCocktail(drink: Drink){
        drink.isFavorite = !drink.isFavorite
        viewModelScope.launch(ioDispatcher) {
            if(drink.isFavorite){
                localRepository.insertFavoriteCocktail(drink)
                Log.d(TAG, "insertFavoriteCocktail: inserted drink into database")
            }else{
                localRepository.deleteFavoriteCocktail(drink)
                Log.d(TAG, "updateFavoriteCocktail: deleted drink from database")
            }
        }
    }
    fun getFavoriteCocktails(){
        viewModelScope.launch(ioDispatcher) {
            _favoriteCocktails.postValue(localRepository.getFavoriteCocktails())
        }
    }

}