package com.mobile.recorduserapp.service

import com.mobile.recorduserapp.data.request.create_user.CreateUser
import com.mobile.recorduserapp.data.response.createuser.CreateUserResponse
import com.mobile.recorduserapp.data.response.foods.FoodResponse
import com.mobile.recorduserapp.data.response.getuser.GetUser
import com.mobile.recorduserapp.data.response.getusers.GetAllUsers
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface Service{


    @Headers(
        "X-Request-ID: 0785f597-2a5c-4b46-9dde-30b3fb7b4016",
        "User-Agent: Application-Name/1.0.0 (Operating System Name 1.0.0)"
    )
    @GET("/pupils?page=1")
    suspend  fun getUsers():Response<GetAllUsers>


    @Headers(
        "X-Request-ID: 0785f597-2a5c-4b46-9dde-30b3fb7b4016",
        "User-Agent: Application-Name/1.0.0 (Operating System Name 1.0.0)"
    )
    @GET("/pupils/{pupilId}")
    suspend  fun getUser(@Path("pupilId") userID:String):Response<GetUser>



    @Headers(
        "X-Request-ID: 677449e5-1076-49eb-b2a7-a9cfa5918437",
        "User-Agent: Application-Name/1.0.0 (Operating System Name 1.0.0)"
    )
    @POST("/pupils")
    suspend  fun createUser(@Body createUser: CreateUser):Response<CreateUserResponse>






}

var retrofit = Retrofit.Builder()
    .baseUrl("https://androidtechnicaltestapi-test.bridgeinternationalacademies.com")
    .addConverterFactory(GsonConverterFactory.create())
    .client(OkHttpClient.Builder().build())
    .build()

object  ApiService{
    val apicall : Service by lazy {
       retrofit.create(Service::class.java)
    }
}

