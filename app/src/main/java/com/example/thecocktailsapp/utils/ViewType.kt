package com.example.thecocktailsapp.utils

import com.example.thecocktailsapp.model.domain.Drink

sealed class ViewType {
    data class DRINK(val drinkList: Drink) : ViewType()
    data class INGREDIENT(val ingredientsList: String, val mesurementList: String) : ViewType()
}
