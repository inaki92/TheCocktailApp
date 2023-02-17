package com.example.thecocktailsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DrinkTable::class],
    version = 1
)
abstract class CocktailsDatabase: RoomDatabase() {

    abstract fun getCocktailsDao(): CocktailsDAO

}