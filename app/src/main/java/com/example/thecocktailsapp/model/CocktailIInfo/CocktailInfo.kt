package com.example.thecocktailsapp.model.CocktailIInfo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CocktailInfo(
    @Json(name = "drinks")
    val drinks: List<DrinkXX>? = listOf()
)