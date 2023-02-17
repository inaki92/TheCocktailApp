package com.example.thecocktailsapp.Res

import android.util.Log
import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.domain.Drink
import com.example.thecocktailsapp.model.domain.mapToDrink
import com.example.thecocktailsapp.utils.FailureResponse
import com.example.thecocktailsapp.utils.NullCocktailResponse
import com.example.thecocktailsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailsRepository{
                fun getCocktailsByName(name: String):
                        Flow<UIState<List<Drink>>>

                fun getCocktailsByAlcohol(alcohol: String):
                        Flow<UIState<List<Drink>>>

                fun getCocktailsByID(id: String):
                        Flow<UIState<Drink>>
}


class CocktailRepositoryImpl @Inject constructor(
            private val cocktailsApi: CocktailsApi
        ): CocktailsRepository{

        override fun getCocktailsByAlcohol(alcohol: String): Flow<UIState<List<Drink>>> = flow{
            emit(UIState.LOADING)
            try {
                val response = cocktailsApi.getCocktailsByAlcoholic(alcohol)
                if(response.isSuccessful){
                    response.body()?.let{
                        emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                    }?: throw NullCocktailResponse() //check if response was null
                }else throw FailureResponse(response.errorBody()?.string())
            }catch (e: Exception){
                emit(UIState.ERROR(e))
                Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
            }
        }



            override fun getCocktailsByName(name: String): Flow<UIState<List<Drink>>> = flow{
                emit(UIState.LOADING)
                try {
                    val response = cocktailsApi.getCocktailsByName(name)
                    if(response.isSuccessful){
                        response.body()?.let {
                            emit(UIState.SUCCESS(it.drinks.mapToDrink()))
                        }?: throw NullCocktailResponse() //check if response was null
                    }else throw FailureResponse(response.errorBody()?.string())
                }catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
                }
            }


            override fun getCocktailsByID(id: String): Flow<UIState<Drink>> = flow{
                emit(UIState.LOADING)
                try {
                    val response = cocktailsApi.getCocktailsByID(id)
                    if(response.isSuccessful){
                        response.body()?.let {
                            emit(UIState.SUCCESS(it.drinks.mapToDrink()[0]))
                        }?: throw NullCocktailResponse() //check if response was null
                    }else throw FailureResponse(response.errorBody()?.string())
                }catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
                }
            }



        }