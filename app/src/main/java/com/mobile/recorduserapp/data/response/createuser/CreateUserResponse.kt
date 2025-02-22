package com.mobile.recorduserapp.data.response.createuser

data class CreateUserResponse(
    val country: String? = "",
    val image: String? = "",
    val latitude: Int? = 0,
    val longitude: Int? = 0,
    val name: String? = "",
    val pupilId: Int? = 0
)