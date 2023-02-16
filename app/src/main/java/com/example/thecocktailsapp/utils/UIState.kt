package com.example.thecocktailsapp.utils

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import com.example.thecocktailsapp.model.domain.Drink

sealed class UIState<out T> {
    object LOADING : UIState<Nothing>()
    data class SUCCESS<T>(val response: T) : UIState<T>()
    data class ERROR(val error: Exception) : UIState<Nothing>()
}
