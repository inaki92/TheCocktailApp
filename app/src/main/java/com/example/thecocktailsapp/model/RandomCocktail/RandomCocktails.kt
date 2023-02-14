package com.example.thecocktailsapp.model.RandomCocktail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RandomCocktails(
    @Json(name = "drinks")
    val drinks: List<DrinkX>? = listOf()
)