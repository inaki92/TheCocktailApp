package com.example.thecocktailsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.thecocktailsapp.Res.CocktailsRepository
import com.example.thecocktailsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CocktailsAppViewModelTest {

    private lateinit var testObject: CocktailsViewModel

    private val mockRepository = mockk<CocktailsRepository>(relaxed = true)
    private val mockDispatcher = UnconfinedTestDispatcher()

    @get:Rule val instantTask = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(mockDispatcher)
        testObject = CocktailsViewModel(mockRepository, mockDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get cocktails when repository retrieves a list of cocktails response returns success state`(){
        // AAA
        every { mockRepository.getCocktails() } returns flowOf(
            UIState.SUCCESS(mockk(), mockk())
        )

        testObject.cocktail.observeForever {
            when(it) {
                is UIState.LOADING -> {

                }
                is UIState.SUCCESS -> {
                    assertEquals(2,it.response )
                }
                is UIState.ERROR -> {

                }
            }
        }
        // ACTION
        testObject.getCocktails()


    }

}