package com.example.thecocktailsapp.model.CocktailsByName


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CocktailsByName(
    @Json(name = "drinks")
    val drinks: List<Drink?>? = null
)