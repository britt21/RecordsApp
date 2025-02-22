package com.mobile.recorduserapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.recorduserapp.data.request.create_user.CreateUser
import com.mobile.recorduserapp.data.response.createuser.CreateUserResponse
import com.mobile.recorduserapp.data.response.foods.FoodResponse
import com.mobile.recorduserapp.data.response.getusers.GetAllUsers
import com.mobile.recorduserapp.service.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel:ViewModel() {


    val liveUsers = MutableLiveData<GetAllUsers>()
    val error = MutableLiveData<String?>()
    val isloading = MutableLiveData<Boolean>()


    private fun showError(message: String?) {
        error.value = message

    }

    fun getUsers() {
        error.value = null
        isloading.value = true
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.getUsers()
                println("MAINREST:: "+response.body())
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    liveUsers.value = response.body()
                    isloading.value = false

                }else{
                    showError("Unknown error occurred")
                    isloading.value = false

                }

            } catch (e: Exception) {
                showError(e.localizedMessage)

                println("IOELOCAL:: "+e.localizedMessage)

                isloading.value = false


            }
        }
    }


    var livecreateuser :MutableLiveData<CreateUserResponse> = MutableLiveData()

    fun createUsers(createUser: CreateUser) {
        isloading.value = true
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.createUser(createUser)
                println("MAINREST:: "+response.body())
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    livecreateuser.value = response.body()
                    isloading.value = false

                }else{
                    showError("Unknown error occurred")
                    isloading.value = false

                }


            } catch (e: Exception) {
                println("IOELOCAL:: "+e.localizedMessage)
                showError(e.localizedMessage)

                isloading.value = false


            }
        }
    }



}