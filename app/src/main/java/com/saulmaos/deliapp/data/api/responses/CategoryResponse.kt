package com.saulmaos.deliapp.data.api.responses

import com.google.gson.annotations.SerializedName

data class CategoryWrapperResponse(
    @SerializedName("categories")
    val categories: List<CategoryResponse> = emptyList()
)

data class CategoryResponse(
    @SerializedName("idCategory")
    val idCategory: String = "",
    @SerializedName("strCategory")
    val category: String = "",
    @SerializedName("strCategoryThumb")
    val image: String = "",
    @SerializedName("strCategoryDescription")
    val description: String = "",
)
