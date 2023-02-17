package com.example.thecocktailsapp.database

import androidx.room.*
import com.example.thecocktailsapp.model.domain.Drink

@Dao
interface CocktailsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(vararg drink: DrinkTable)

    @Query("SELECT * FROM drinks WHERE isFavorite = true")
    suspend fun getFavoriteCocktails(): List<DrinkTable>

    @Delete
    suspend fun deleteCocktail(vararg drink: DrinkTable)

}