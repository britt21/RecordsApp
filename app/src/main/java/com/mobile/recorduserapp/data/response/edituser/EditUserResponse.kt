package com.mobile.recorduserapp.data.response.edituser

data class EditUserResponse(
    val country: String? = "",
    val image: String? = "",
    val latitude: Double? = 0.0,
    val longitude: Double? = 0.0,
    val name: String? = "",
    val pupilId: Int? = 0
)