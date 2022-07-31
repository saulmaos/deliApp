package com.saulmaos.deliapp.ui.mealdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saulmaos.deliapp.data.api.responses.MealDetailResponse
import com.saulmaos.deliapp.domain.GetMealDetailsUseCase
import com.saulmaos.deliapp.domain.GetRandomMealDetailsUseCase
import com.saulmaos.deliapp.utils.RANDOM_MEAL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetailsUseCase,
    private val getRandomMealDetailsUseCase: GetRandomMealDetailsUseCase
) : ViewModel() {
    private val _mealDetailsState = MutableLiveData<MealDetailsState>()
    val mealDetailsState: LiveData<MealDetailsState> = _mealDetailsState

    fun getMealDetails(mealId: String) = viewModelScope.launch {
        try {
            _mealDetailsState.value = MealDetailsState.Loading
            val details: MealDetailResponse? =
                if (mealId == RANDOM_MEAL) getRandomMealDetailsUseCase()
                else getMealDetailsUseCase(mealId)

            if (details != null) _mealDetailsState.value = MealDetailsState.Success(
                details, formatIngredientsChips(details), formatMeasuresChips(details)
            )
            else _mealDetailsState.value = MealDetailsState.Failure
        } catch (e: Exception) {
            e.printStackTrace()
            _mealDetailsState.value = MealDetailsState.Failure
        }
    }

    private fun formatIngredientsChips(details: MealDetailResponse): List<String> {
        return createList(
            details.ingredient1,
            details.ingredient2,
            details.ingredient3,
            details.ingredient4,
            details.ingredient5,
            details.ingredient6,
            details.ingredient7,
            details.ingredient8,
            details.ingredient9,
            details.ingredient10,
            details.ingredient11,
            details.ingredient12,
            details.ingredient13,
            details.ingredient14,
            details.ingredient15,
            details.ingredient16,
            details.ingredient17,
            details.ingredient18,
            details.ingredient19,
            details.ingredient20,
        )
    }

    private fun formatMeasuresChips(details: MealDetailResponse): List<String> {
        return createList(
            details.measure1,
            details.measure2,
            details.measure3,
            details.measure4,
            details.measure5,
            details.measure6,
            details.measure7,
            details.measure8,
            details.measure9,
            details.measure10,
            details.measure11,
            details.measure12,
            details.measure13,
            details.measure14,
            details.measure15,
            details.measure16,
            details.measure17,
            details.measure18,
            details.measure19,
            details.measure20,
        )
    }

    private fun createList(vararg items: String): List<String> {
        val values = mutableListOf<String>()
        for (item in items) {
            if (item.isNotBlank()) values.add(item)
        }
        return values
    }
}

sealed class MealDetailsState {
    object Failure : MealDetailsState()
    object Loading : MealDetailsState()
    data class Success(
        val details: MealDetailResponse,
        val ingredients: List<String>,
        val measures: List<String>
    ) : MealDetailsState()
}
