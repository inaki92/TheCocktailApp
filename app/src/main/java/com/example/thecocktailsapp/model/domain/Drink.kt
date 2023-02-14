package com.example.thecocktailsapp.model.domain

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse


data class Drink(
    val idDrink: String?,
    val alcoholic: String?,
    val category: String?,
    val drinkName: String?,
    val drinkThumb: String?,
    val glass: String?,
    val ingredients: List<String?>?,
    val instructions: String?,
    val measures: List<String?>?
)

fun CocktailsResponse?.mapToDrink(): Drink{

    //creating ingredients list
    val ingredientsList: MutableList<String?> =
        mutableListOf(
            this?.strIngredient1,
            this?.strIngredient2,
            this?.strIngredient3,
            this?.strIngredient4,
            this?.strIngredient5,
            this?.strIngredient6,
            this?.strIngredient7,
            this?.strIngredient8,
            this?.strIngredient9,
            this?.strIngredient10,
            this?.strIngredient11,
            this?.strIngredient12,
            this?.strIngredient13,
            this?.strIngredient14,
            this?.strIngredient15
        )
    ingredientsList.forEach{
        if(it == null)
            ingredientsList.remove(it)
    }

    val measuresList: MutableList<String?> =
        mutableListOf(
            this?.strMeasure1,
            this?.strMeasure2,
            this?.strMeasure3,
            this?.strMeasure4,
            this?.strMeasure5,
            this?.strMeasure6,
            this?.strMeasure7,
            this?.strMeasure8,
            this?.strMeasure9,
            this?.strMeasure10,
            this?.strMeasure11,
            this?.strMeasure12,
            this?.strMeasure13,
            this?.strMeasure14,
            this?.strMeasure15
        )
    measuresList.forEach{
        if(it == null)
            measuresList.remove(it)
    }

    return Drink(
        idDrink = this?.idDrink,
        alcoholic = this?.strAlcoholic,
        category = this?.strCategory,
        drinkName = this?.strDrink,
        drinkThumb = this?.strDrinkThumb+"/preview",
        glass = this?.strGlass,
        ingredients = ingredientsList,
        measures = measuresList,
        instructions = this?.strInstructions
    )

}
