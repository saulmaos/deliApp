package com.saulmaos.deliapp.data

import com.saulmaos.deliapp.data.api.DeliService
import com.saulmaos.deliapp.data.api.responses.CategoryResponse
import com.saulmaos.deliapp.data.api.responses.MealDetailResponse
import com.saulmaos.deliapp.data.api.responses.MealResponse
import javax.inject.Inject

class DeliRepository @Inject constructor(private val deliService: DeliService) {
    suspend fun getCategories(): List<CategoryResponse> = deliService.getCategories().categories

    suspend fun getMeals(category: String): List<MealResponse> = deliService.getMeals(category).meals

    suspend fun getMealDetails(mealId: String): List<MealDetailResponse> = deliService.getMealDetails(mealId).meals

    suspend fun getRandomMealDetails(): List<MealDetailResponse> = deliService.getRandomMealDetails().meals
}
