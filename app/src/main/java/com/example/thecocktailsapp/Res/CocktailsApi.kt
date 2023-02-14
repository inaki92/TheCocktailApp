package com.example.thecocktailsapp.Rest

import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsApi:  {

    @GET(cocktailsByName)
    suspend fun getCocktailsByName(
        @Query("s") name: String
    ):Response<CocktailInfo>


    @GET(cocktailsByAlcohol)
        suspend fun getCocktailsByAlcohol(
            @Query("a") name: String
        ):Response<CocktailInfo>

    @GET(cocktailsByRandom)
        suspend fun getCocktailsByRandom(
            @Query("i") name: String
        ):Response<CocktailInfo>

    @GET(cocktailsByRandomList)
        suspend fun getCocktailsByRandomList(
            @Query("i") name: String
        ):Response<CocktailInfo>




companion object {

        const val BASE_URL = "http://www.thecocktaildb.com/api/json/v1/1/"
        private const val cocktailsByName = "search.php"
        private const val cocktailsByAlcohol = "filter.php"
        private const val cocktailsByRandom = "lookup.php"
        private const val cocktailsByRandomList = "lookup.php"


    }
}