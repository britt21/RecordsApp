package com.mobile.recorduserapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.recorduserapp.data.request.create_user.CreateUser
import com.mobile.recorduserapp.data.request.edituser.EditUser
import com.mobile.recorduserapp.data.response.createuser.CreateUserResponse
import com.mobile.recorduserapp.data.response.edituser.EditUserResponse
import com.mobile.recorduserapp.data.response.foods.FoodResponse
import com.mobile.recorduserapp.data.response.getuser.GetUser
import com.mobile.recorduserapp.data.response.getusers.GetAllUsers
import com.mobile.recorduserapp.service.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel:ViewModel() {



    val liveUsers = MutableLiveData<GetAllUsers?>()
    val error = MutableLiveData<String?>()
    val isloading = MutableLiveData<Boolean>()


    val edituser  by  mutableStateOf(EditUser())
    private fun showError(message: String?) {
        error.postValue( message)

    }

    fun clearlog(){
        livecreateuser.postValue(null)
        liveEditUser.postValue(null)
        liveUser.postValue(null)
    }


    fun getUsers() {
        println("FETCHING:;")
        error.postValue( null)
        liveUsers.postValue( null)
        isloading.postValue( true)
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.getUsers()
                println("MAINREST:: "+response.body())
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    liveUsers.postValue(response.body())
                    isloading.postValue( false)

                }else{
                    showError("${response.code()}: An error occurred")
                    isloading.postValue( false)

                }

            } catch (e: Exception) {
                showError(e.localizedMessage)

                println("IOELOCAL:: "+e.localizedMessage)

                isloading.postValue( false)


            }
        }
    }

    val liveUser = MutableLiveData<GetUser?>()
    val liveEditUser = MutableLiveData<EditUserResponse?>()
    val liveDeleteUser = MutableLiveData<Int?>()

    fun getUser(userID:String) {
        error.postValue( null)
        isloading.postValue(true)
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.getUser(userID)
                println("USER  MAINREST:: "+response.body())
                println("USER  MAINREST:: "+response.raw().request.url)
                println("USER  MAINREST:: "+response.raw().request.body)
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    liveUser.postValue(response.body())
                    isloading.postValue( false)

                }else if (response.code() == 404){

                    showError("User not found")
                    isloading.postValue( false)

                }else{

                    showError("Unknown error occurred")
                    isloading.postValue( false)

                }

            } catch (e: Exception) {
                showError(e.localizedMessage)

                println("IOELOCAL:: "+e.localizedMessage)

                isloading.value = false


            }
        }
    }

    fun deleteuser(userID:String) {
        println("DELETEING")
        error.postValue( null)
        isloading.postValue(true)
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.deleteUser(userID)
                println("USER  MAINREST:: "+response.body())
                println("USER  MAINREST:: "+response.raw().request.url)
                println("USER  MAINREST:: "+response.raw().request.body)
                var initcode = 200..209
                if (response.code() == 200 || response.code() == 201|| response.code() == 203|| response.code() == 204){
println("DELETESUCCESS "+response.code())
                    println("GOTHERE:; "+response.body())
                    liveDeleteUser.postValue(response.code())
                    isloading.postValue( false)

                }else if (response.code() == 404){

                    showError("User not found")
                    isloading.postValue( false)

                }else{

                    showError("An error occurred")
                    isloading.postValue( false)

                }

            } catch (e: Exception) {
                showError(e.localizedMessage)

                println("IOELOCAL:: "+e.localizedMessage)

                isloading.value = false


            }
        }
    }



    fun editUser(userID:String,editUser: EditUser) {
        println("EDITING_USER "+editUser +" ")
        error.postValue( null)
        isloading.postValue(true)
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.editUser(userID,editUser)
                println("USER  MAINREST:: "+response.body())
                println("USER  MAINREST:: "+response.raw().request.url)
                println("USER  MAINREST:: "+response.raw().request.body)
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    liveEditUser.postValue(response.body())
                    isloading.postValue( false)

                }else if (response.code() == 404){

                    showError("User not found")
                    isloading.postValue( false)

                }else{

                    showError("Unknown error occurred")
                    isloading.postValue( false)

                }

            } catch (e: Exception) {
                showError(e.localizedMessage)

                println("IOELOCAL:: "+e.localizedMessage)

                isloading.value = false


            }
        }
    }



    var livecreateuser :MutableLiveData<CreateUserResponse?> = MutableLiveData()

    fun createUsers(createUser: CreateUser) {
        println("CREATE:: "+createUser)
        livecreateuser.value = null
        error.value = null
        isloading.value = true
        viewModelScope.launch {
            try {
                val response = ApiService.apicall.createUser(createUser)
                println("MAINREST:: "+response.body())
                println("MAINREST:: "+response.message())
                if (response.code() == 200 || response.code() == 201){

                    println("GOTHERE:; "+response.body())
                    livecreateuser.value = response.body()
                    isloading.value = false

                }else{
                    // Error response (400 or other non-2xx codes)
                    val errorBody = response.errorBody()?.string()
                    println("ERROR BODY:: $errorBody")
                    val errorMessage = parseErrorMessage(errorBody)

                    showError("${response.code()} ${errorBody}\nUnknown error occurred")
                    isloading.value = false

                }


            } catch (e: Exception) {
                println("IOELO CAL:: "+e.localizedMessage)
                showError(e.localizedMessage)

                isloading.value = false


            }
        }
    }



}
fun parseErrorMessage(errorBody: String?): String {
    return try {
        val jsonObject = JSONObject(errorBody ?: "")
        val errors = jsonObject.getJSONObject("errors")
        val latitudeErrors = errors.optJSONArray("Latitude")?.join(", ") ?: ""
        val longitudeErrors = errors.optJSONArray("Longitude")?.join(", ") ?: ""

        listOf(latitudeErrors, longitudeErrors)
            .filter { it.isNotEmpty() }
            .joinToString("\n")
    } catch (e: JSONException) {
        "Unexpected error format."
    }
}
