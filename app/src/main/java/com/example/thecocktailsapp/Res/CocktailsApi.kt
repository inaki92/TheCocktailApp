package com.example.thecocktailsapp.Rest

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {

    @GET(Cocktails)
    suspend fun getCocktails(
        @Query("s") name: String
    ):Response<CocktailInfo>

companion object {

        const val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        private const val Cocktails = "search.php"


    }
}