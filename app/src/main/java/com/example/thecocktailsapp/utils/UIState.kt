package com.example.thecocktailsapp.utils

import com.example.thecocktailsapp.model.domain.Drink

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val response: List <Drink>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
