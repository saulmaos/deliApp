package com.saulmaos.deliapp.data.api

import com.saulmaos.deliapp.data.api.responses.CategoryWrapperResponse
import com.saulmaos.deliapp.data.api.responses.MealDetailWrapperResponse
import com.saulmaos.deliapp.data.api.responses.MealWrapperResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DeliService {
    @GET(DELI_CATEGORIES)
    suspend fun getCategories(): CategoryWrapperResponse

    @GET(DELI_MEALS)
    suspend fun getMeals(@Query("c") category: String): MealWrapperResponse

    @GET(DELI_MEAL_DETAIL)
    suspend fun getMealDetails(@Query("i") mealId: String): MealDetailWrapperResponse

    @GET(DELI_MEAL_RANDOM)
    suspend fun getRandomMealDetails(): MealDetailWrapperResponse
}
