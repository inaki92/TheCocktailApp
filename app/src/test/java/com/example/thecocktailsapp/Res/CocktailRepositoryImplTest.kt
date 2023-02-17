package com.example.thecocktailsapp.Res

import com.example.thecocktailsapp.Rest.CocktailsApi
import com.example.thecocktailsapp.model.CocktailIInfo.CocktailInfo
import com.example.thecocktailsapp.utils.UIState
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


internal class MusicRepositoryImplTest {

    private lateinit var testObject: CocktailsRepository
    private val mockServiceApi = mockk<CocktailsApi>(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {

        Dispatchers.setMain(testDispatcher)
        testObject = CocktailRepositoryImpl(mockServiceApi)

    }

    @After
    fun tearDown() {

        Dispatchers.resetMain()
        clearAllMocks()

    }

    @Test
    fun `get all cocktails when server is a success response returns cocktailsInf`() {
        //ASSIGN
        coEvery { mockServiceApi.getAllCocktail() } returns mockk {
            every { isSuccessful } returns true
            every { body() } returns CocktailInfo(drinks = listOf(mockk()))


        //ACTION
        var state: UIState = UIState.LOADING
        val job = testScope.launch {
            testObject.getCocktails().collect {
                state = it
            }
        }

        //ASSESS
        assert(state is UIState.SUCCESS)

    }
}
}
