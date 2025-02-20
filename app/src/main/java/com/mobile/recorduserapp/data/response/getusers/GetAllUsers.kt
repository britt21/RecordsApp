package com.mobile.recorduserapp.data.response.getusers

data class GetAllUsers(
    val itemCount: Int?,
    val items: List<Item?>?,
    val pageNumber: Int?,
    val totalPages: Int?
)