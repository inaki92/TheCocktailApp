package com.example.thecocktailsapp.model.domain

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailResponse

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
    val isFavorite: Boolean = false
)

fun List<CocktailResponse>?.mapToDrink(): List<Drink> {
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
            measuresList.filterNotNull()
        )
    }?:return emptyList()

}