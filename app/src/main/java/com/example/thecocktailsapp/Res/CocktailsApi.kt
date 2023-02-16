package com.example.thecocktailsapp.Rest

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {



    //Get random
    @GET(COCKTAILS_BY_ALCOHOL_CONTENT)
    suspend fun getAllCocktail(
        @Query("a") alcohol: String = "Alcoholic"
    ): Response<CocktailInfo>

    //Get Non Alcoholic
    @GET(COCKTAILS_BY_ALCOHOL_CONTENT)
    suspend fun getAllCocktailNonAlcoholic(
        @Query("a") Noalcohol: String = "Non_Alcoholic"
    ): Response<CocktailInfo>


    @GET(COCKTAILS_BY_NAME)
    suspend fun getCocktailByName(
        @Query("s") name: String
    ): Response<CocktailInfo>


    @GET(COCKTAILS_BY_ID)
    suspend fun getCocktailById(
        @Query("i") id: String
    ): Response<CocktailInfo>


    companion object {

        const val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        private const val COCKTAILS_BY_NAME = "search.php"
        private const val COCKTAILS_BY_ALCOHOL_CONTENT = "filter.php"
        private const val COCKTAILS_BY_ID = "lookup.php"


    }
}
