package com.saulmaos.deliapp.domain

import com.saulmaos.deliapp.data.DeliRepository
import com.saulmaos.deliapp.data.api.responses.CategoryResponse
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val deliRepository: DeliRepository) {
    suspend operator fun invoke(): List<CategoryResponse> = deliRepository.getCategories()
}