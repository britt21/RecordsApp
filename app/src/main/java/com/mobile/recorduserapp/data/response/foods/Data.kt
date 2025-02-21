package com.mobile.recorduserapp.data.response.foods

data class Data(
    val calories: Int?,
    val category: Category?,
    val category_id: Int?,
    val created_at: String?,
    val description: String?,
    val foodImages: List<FoodImage?>?,
    val foodTags: List<String?>?,
    val id: Int?,
    val name: String?,
    val updated_at: String?
)