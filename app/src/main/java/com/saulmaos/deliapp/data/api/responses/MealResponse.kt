package com.saulmaos.deliapp.data.api.responses

import com.google.gson.annotations.SerializedName

data class MealWrapperResponse(
    @SerializedName("meals")
    val meals: List<MealResponse> = emptyList()
)

data class MealResponse(
    @SerializedName("strMeal")
    val meal: String = "",
    @SerializedName("strMealThumb")
    val image: String = "",
    @SerializedName("idMeal")
    val idMeal: String = "",
)