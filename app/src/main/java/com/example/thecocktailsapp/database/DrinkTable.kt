package com.example.thecocktailsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thecocktailsapp.model.domain.Drink

@Entity(tableName = "drinks")
data class DrinkTable(
    @PrimaryKey
    val idDrink: String = "",
    val drinkName: String = "",
    val drinkThumb: String = "",
    val isFavorite: Boolean = false
)

fun Drink.mapToDrinkTable(): DrinkTable{
    return DrinkTable(
        this.idDrink,
        this.drinkName,
        this.drinkThumb,
        this.isFavorite
    )
}
