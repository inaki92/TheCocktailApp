package com.example.thecocktailsapp.DI

import com.example.thecocktailsapp.Res.CocktailRepositoryImpl
import com.example.thecocktailsapp.Res.CocktailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent:: class)
abstract class RepositoryModule {

    @Binds
    abstract fun ProvidesCocktailsRespository(
        CocktailsRepositortyImpl: CocktailRepositoryImpl
    ): CocktailsRepository
}