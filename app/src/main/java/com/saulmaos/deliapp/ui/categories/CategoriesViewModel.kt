package com.saulmaos.deliapp.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulmaos.deliapp.data.api.responses.CategoryResponse
import com.saulmaos.deliapp.domain.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel() {
    private val _categories = MutableLiveData<CategoriesState>()
    val categories: LiveData<CategoriesState> = _categories

    fun getCategories() = viewModelScope.launch {
        try {
            _categories.value = CategoriesState.Loading
            _categories.value = CategoriesState.Success(getCategoriesUseCase())
        } catch (e: Exception) {
            e.printStackTrace()
            _categories.value = CategoriesState.Failure
        }
    }
}

sealed class CategoriesState {
    object Failure: CategoriesState()
    object Loading: CategoriesState()
    data class Success(val categories: List<CategoryResponse>): CategoriesState()
}