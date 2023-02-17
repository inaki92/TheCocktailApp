package com.example.thecocktailsapp.model.domain

import com.example.thecocktailsapp.database.DrinkTable
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailResponse
import com.google.gson.Gson

data class Drink(
    val idDrink: String = "",
    val alcoholic: String = "",
    val category: String = "",
    val drinkName: String = "",
    val drinkThumb: String = "",
    val glass: String = "",
    val ingredients: List<String> = listOf(),
    val instructions: String = "",
    val measures: List<String> = listOf(),
    var isFavorite: Boolean = false
)

fun List<CocktailResponse>?.mapToDrink(isInFavorite: Boolean): List<Drink> {
    return this?.map {
        //creating ingredients list
        val ingredientsList: MutableList<String?> =
            mutableListOf(
                it.strIngredient1,
                it.strIngredient2,
                it.strIngredient3,
                it.strIngredient4,
                it.strIngredient5,
                it.strIngredient6,
                it.strIngredient7,
                it.strIngredient8,
                it.strIngredient9,
                it.strIngredient10,
                it.strIngredient11,
                it.strIngredient12,
                it.strIngredient13,
                it.strIngredient14,
                it.strIngredient15
            )

        //creating measures list
        val measuresList: MutableList<String?> =
            mutableListOf(
                it.strMeasure1,
                it.strMeasure2,
                it.strMeasure3,
                it.strMeasure4,
                it.strMeasure5,
                it.strMeasure6,
                it.strMeasure7,
                it.strMeasure8,
                it.strMeasure9,
                it.strMeasure10,
                it.strMeasure11,
                it.strMeasure12,
                it.strMeasure13,
                it.strMeasure14,
                it.strMeasure15
            )

        Drink(
            it.idDrink ?: Math.random().toInt().toString(),
            it.strAlcoholic ?: "",
            it.strCategory ?: "",
            it.strDrink ?: "",
            it.strDrinkThumb + "/preview",
            it.strGlass ?: "",
            ingredientsList.filterNotNull(),
            it.strInstructions ?: "",
            measuresList.filterNotNull(),
            isInFavorite
        )
    }?:return emptyList()
}

fun List<DrinkTable>.mapToDrinkFromTable(): List<Drink>{
    val gson = Gson()
    return this.map {
        Drink(
            it.idDrink,
            it.alcoholic,
            it.category,
            it.drinkName,
            it.drinkThumb,
            it.glass,
            gson.fromJson<List<String>>(it.ingredients,List::class.java),
            it.instructions,
            gson.fromJson<List<String>>(it.measures,List::class.java),
            it.isFavorite
        )
    }
}