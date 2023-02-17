package com.example.thecocktailsapp.model.CocktailIInfo


import com.google.gson.annotations.SerializedName

data class CocktailInfo(
    @SerializedName("drinks")
    val drinks: List<CocktailResponse>? = null
)