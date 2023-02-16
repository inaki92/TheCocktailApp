package com.example.thecocktailsapp.Res



import android.util.Log
import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import com.example.thecocktailsapp.model.domain.mapToDrink
import com.example.thecocktailsapp.utils.FailureResponse
import com.example.thecocktailsapp.utils.NullCocktailResponse
import com.example.thecocktailsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailsRepository {
    fun getCocktails(): Flow<UIState>
    fun getAllCocktailNonAlcoholic(): Flow<UIState>
    fun getCocktailsByName(query: String): Flow<UIState>
    fun getCocktailsById(id: String): Flow<UIState>

}


class CocktailRepositoryImpl @Inject constructor(
    private val api: CocktailsApi
) : CocktailsRepository {


    // Random Cocktails
    override fun getCocktails(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getAllCocktail()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d(TAG, "getCocktails: $it")
                    emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                } ?: throw NullCocktailResponse() //check if response was null
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
            Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
        }
    }

    override fun getAllCocktailNonAlcoholic(): Flow<UIState> = flow{
        emit(UIState.LOADING)
        try {
            val response = api.getAllCocktailNonAlcoholic()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                } ?: throw NullCocktailResponse() //check if response was null
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
            Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
        }
    }

    //Get by name
    override fun getCocktailsByName(query: String): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = api.getCocktailByName(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                } ?: throw NullCocktailResponse() //check if response was null
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
            Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
        }

    }


    //Get id
    override fun getCocktailsById(id: String): Flow<UIState> = flow {

        emit(UIState.LOADING)
        try {
            val response = api.getCocktailById(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                } ?: throw NullCocktailResponse() //check if response was null
            } else throw FailureResponse(response.errorBody()?.string())
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
            Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
        }
        }
        }


