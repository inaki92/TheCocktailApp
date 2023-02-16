package com.example.thecocktailsapp.Rest

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi {


    @GET(COCKTAILS_BY_SEARCH)
        suspend fun getCocktailsByAlcoholic(
            @Query("a") alcohol: String
        ):Response<CocktailInfo>

    @GET(COCKTAILS_BY_ID)
        suspend fun getCocktailsByID(
            @Query("i") id: String
        ):Response<CocktailInfo>

    @GET(COCKTAILS_BY_NAME)
        suspend fun getCocktailsByName(
            @Query("s") name: String
        ):Response<CocktailInfo>




companion object {

        const val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        private const val COCKTAILS_BY_NAME= "search.php"
        private const val COCKTAILS_BY_ID = "lookup.php"
        private const val COCKTAILS_BY_SEARCH = "filter.php"



    }
}