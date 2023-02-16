package com.example.thecocktailsapp.utils

class NullCocktailResponse(message: String = "Cocktail response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)
