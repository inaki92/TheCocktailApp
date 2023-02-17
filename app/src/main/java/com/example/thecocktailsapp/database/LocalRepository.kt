package com.example.thecocktailsapp.database

import android.util.Log
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.utils.UIState
import javax.inject.Inject

private const val TAG = "LocalRepository"
interface LocalRepository {
    suspend fun insertFavoriteCocktail(drink: Drink)
    suspend fun getFavoriteCocktails(): UIState<List<DrinkTable>>
    suspend fun deleteFavoriteCocktail(drink: Drink)
    suspend fun getCocktailById(drinkId: String): DrinkTable?

}
class LocalRepositoryImpl @Inject constructor(
    private val cocktailsDAO: CocktailsDAO
): LocalRepository{

    override suspend fun insertFavoriteCocktail(drink: Drink) {
        try{
            val drinkTable = drink.mapToDrinkTable()
            cocktailsDAO.insertCocktail(drinkTable)
        }catch(e: Exception){
            Log.e(TAG, "insertCocktail: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getFavoriteCocktails(): UIState<List<DrinkTable>> {
        return try {
            UIState.SUCCESS(cocktailsDAO.getFavoriteCocktails())
        } catch (e: Exception){
            UIState.ERROR(e)
        }
    }

    override suspend fun deleteFavoriteCocktail(drink: Drink) {
        try {
            val drinkTable = drink.mapToDrinkTable()
            cocktailsDAO.deleteCocktail(drinkTable)
        }catch (e: Exception){
            Log.e(TAG, "deleteFavoriteCocktail: ${e.localizedMessage}", e)
        }
    }

    override suspend fun getCocktailById(drinkId: String): DrinkTable? {
        return try {
            cocktailsDAO.getCocktail(drinkId)
        }catch (e: java.lang.Exception){
            Log.e(TAG, "getCocktailById: ${e.localizedMessage}", e)
            null
        }
    }

}