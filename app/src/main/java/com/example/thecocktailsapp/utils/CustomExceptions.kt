package com.example.starwarsmvvm.utils

class NullCocktailResponse(message: String = "People response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)