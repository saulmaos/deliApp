package com.saulmaos.deliapp.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulmaos.deliapp.data.api.responses.MealResponse
import com.saulmaos.deliapp.domain.GetMealsUseCase
import com.saulmaos.deliapp.ui.categories.CategoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase
) : ViewModel() {
    private val _mealsState = MutableLiveData<MealsState>()
    val mealsState: LiveData<MealsState> = _mealsState

    fun getMeals(category: String) = viewModelScope.launch {
        try {
            _mealsState.value = MealsState.Loading
            _mealsState.value = MealsState.Success(getMealsUseCase(category))
        } catch (e: Exception) {
            e.printStackTrace()
            _mealsState.value = MealsState.Failure
        }
    }
}

sealed class MealsState {
    object Failure: MealsState()
    object Loading: MealsState()
    data class Success(val meals: List<MealResponse>): MealsState()
}
