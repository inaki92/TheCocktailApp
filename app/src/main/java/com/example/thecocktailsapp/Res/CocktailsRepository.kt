package com.example.thecocktailsapp.Res

import android.util.Log
import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CocktailsRepository{
                fun getCocktails():
                        Flow<UIState<List<CocktailsResponse>>>
}


class CocktailRepositoryImpl @Inject constructor(
            private val cocktailsApi: CocktailsApi
        ): CocktailsRepository{

            override fun getCocktails(): Flow<UIState<List<CocktailsResponse>>> = flow{
                emit(UIState.LOADING)
                try {
                    val response = CocktailsAPI.getCocktails()
                    if(response.isSuccessful){
                        response.body()?.let {
                            emit(UIState.SUCCESS(it))
                        }?: throw NullCocktailsException() //check if response was null
                    }else throw FailureResponseException(response.errorBody()?.string())
                }catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "getCocktails: ${e.localizedMessage}", e)
                }
            }


        }