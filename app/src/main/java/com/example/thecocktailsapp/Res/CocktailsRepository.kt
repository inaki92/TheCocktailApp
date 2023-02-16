package com.example.thecocktailsapp.Res

import android.util.Log
import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailsResponse
import com.example.thecocktailsapp.utils.FailureResponse
import com.example.thecocktailsapp.utils.NullCocktailResponse
import com.example.thecocktailsapp.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "CocktailsRepository"

interface CocktailsRepository{
                fun getCocktailsByName(name: String):
                        Flow<UIState<CocktailInfo>>

                fun getCocktailsByAlcohol(alcohol: String):
                        Flow<UIState<CocktailInfo>>

                fun getCocktailsByID(id: String):
                        Flow<UIState<CocktailInfo>>


}


class CocktailRepositoryImpl @Inject constructor(
            private val cocktailsApi: CocktailsApi
        ): CocktailsRepository{

        override fun getCocktailsByAlcohol(alcohol: String): Flow<UIState<CocktailInfo>> = flow{
            emit(UIState.LOADING)
            try {
                val response = cocktailsApi.getCocktailsByAlcoholic(alcohol)
                if(response.isSuccessful){
                    response.body()?.let {
                        emit(UIState.SUCCESS(it))
                    }?: throw NullCocktailResponse() //check if response was null
                }else throw FailureResponse(response.errorBody()?.string())
            }catch (e: Exception){
                emit(UIState.ERROR(e))
                Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
            }
        }



            override fun getCocktailsByName(name: String): Flow<UIState<CocktailInfo>> = flow{
                emit(UIState.LOADING)
                try {
                    val response = cocktailsApi.getCocktailsByName(name)
                    if(response.isSuccessful){
                        response.body()?.let {
                            emit(UIState.SUCCESS(it))
                        }?: throw NullCocktailResponse() //check if response was null
                    }else throw FailureResponse(response.errorBody()?.string())
                }catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
                }
            }


            override fun getCocktailsByID(id: String): Flow<UIState<CocktailInfo>> = flow{
                emit(UIState.LOADING)
                try {
                    val response = cocktailsApi.getCocktailsByID(id)
                    if(response.isSuccessful){
                        response.body()?.let {
                            emit(UIState.SUCCESS(it))
                        }?: throw NullCocktailResponse() //check if response was null
                    }else throw FailureResponse(response.errorBody()?.string())
                }catch (e: Exception){
                    emit(UIState.ERROR(e))
                    Log.e(TAG, "getCocktailsByName: ${e.localizedMessage}", e)
                }
            }



        }