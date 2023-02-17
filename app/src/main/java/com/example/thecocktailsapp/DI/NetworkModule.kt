package com.example.thecocktailsapp.DI

import com.example.thecocktailsapp.Rest.CocktailsApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//Provides the necessary elements for the network
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


//Provides Gson
@Provides
fun providesGson (): Gson = Gson()

//Method to provide Retrofit
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(CocktailsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

//Method to provide OkHttpClient
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

//Method to provide HttpLoggingInterceptor
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
//Method to provide Music Servcies
    @Provides
    fun providesApiService(retrofit: Retrofit): CocktailsApi{
        return retrofit.create(CocktailsApi::class.java)
    }

//Method that provides the IO Dispatcher
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher= Dispatchers.IO








}