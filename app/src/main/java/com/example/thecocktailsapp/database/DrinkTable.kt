package com.example.thecocktailsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thecocktailsapp.model.domain.Drink
import com.google.gson.Gson

@Entity(tableName = "drinks")
data class DrinkTable(
    @PrimaryKey
    val idDrink: String = "",
    val alcoholic: String = "",
    val category: String = "",
    val drinkName: String = "",
    val drinkThumb: String = "",
    val glass: String = "",
    val ingredients: String = "",
    val instructions: String = "",
    val measures: String = "",
    var isFavorite: Boolean = true
)

fun Drink.mapToDrinkTable(): DrinkTable{
    val gson = Gson()
    return DrinkTable(
        this.idDrink,
        this.alcoholic,
        this.category,
        this.drinkName,
        this.drinkThumb,
        this.glass,
        gson.toJson(this.ingredients),
        this.instructions,
        gson.toJson(this.measures),
        this.isFavorite
    )
}
