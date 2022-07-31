package com.saulmaos.deliapp.domain

import com.saulmaos.deliapp.data.DeliRepository
import com.saulmaos.deliapp.data.api.responses.MealResponse
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(private val repository: DeliRepository) {
    suspend operator fun invoke(category: String) : List<MealResponse> = repository.getMeals(category)
}
