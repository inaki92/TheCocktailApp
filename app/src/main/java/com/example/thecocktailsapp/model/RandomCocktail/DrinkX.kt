package com.example.thecocktailsapp.model.RandomCocktail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DrinkX(
    @Json(name = "idDrink")
    val idDrink: String? = null,
    @Json(name = "strDrink")
    val strDrink: String? = null,
    @Json(name = "strDrinkThumb")
    val strDrinkThumb: String? = null
)