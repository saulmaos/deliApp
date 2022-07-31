package com.saulmaos.deliapp.domain

import com.saulmaos.deliapp.data.DeliRepository
import com.saulmaos.deliapp.data.api.responses.MealDetailResponse
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: DeliRepository) {
    suspend operator fun invoke(mealId: String): MealDetailResponse? = repository.getMealDetails(mealId).firstOrNull()
}
