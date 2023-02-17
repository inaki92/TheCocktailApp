package com.example.thecocktailsapp.DI

import android.content.Context
import androidx.room.Room
import com.example.thecocktailsapp.database.CocktailsDAO
import com.example.thecocktailsapp.database.CocktailsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesCocktailsDatabase(
        @ApplicationContext context: Context
    ): CocktailsDatabase =
        Room.databaseBuilder(
            context,
            CocktailsDatabase::class.java,
            "cocktails-db"
        ).build()

    @Provides
    fun providesCocktailsDao(
        cocktailsDatabase: CocktailsDatabase
    ): CocktailsDAO = cocktailsDatabase.getCocktailsDao()


}