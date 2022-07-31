package com.saulmaos.deliapp.domain

import com.saulmaos.deliapp.data.DeliRepository
import com.saulmaos.deliapp.data.api.responses.MealDetailResponse
import javax.inject.Inject

class GetRandomMealDetailsUseCase @Inject constructor(
    private val repository: DeliRepository
) {
    suspend operator fun invoke(): MealDetailResponse? = repository.getRandomMealDetails().firstOrNull()
}
