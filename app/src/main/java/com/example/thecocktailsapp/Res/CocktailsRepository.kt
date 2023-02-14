package com.example.thecocktailsapp.Res

import android.util.Log
import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailsRepository{
                fun getCocktailsByName():
                        Flow<UIState<CocktailsResponse>>

                fun getCocktailsByAlcohol():
                        Flow<UIState<CocktailsResponse>>

                fun getCocktailsByRandom():
                        Flow<UIState<CocktailsResponse>>

                fun getCocktailsByRandomList():
                        Flow<UIState<CocktailsResponse>>


}


class CocktailRepositoryImpl @Inject constructor(
            private val cocktailsApi: CocktailsApi
        ): CocktailsRepository{

            override fun getCocktailsByName(): = flow{
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
                    Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
                }
            }

        override fun getCocktailsByAlcohol(): Flow<UIState<List<CocktailsResponse>>> = flow{
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
                Log.e(TAG, "getCocktailsByAlcohol: ${e.localizedMessage}", e)
            }
        }


        override fun getCocktailsByRandom(): Flow<UIState<List<CocktailsResponse>>> = flow{
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
                Log.e(TAG, "getCocktailsByRandom: ${e.localizedMessage}", e)
            }
        }

        override fun getCocktailsByRandomList(): Flow<UIState<List<CocktailsResponse>>> = flow{
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
                Log.e(TAG, "getCocktailsByRandomList: ${e.localizedMessage}", e)
            }
        }



        }