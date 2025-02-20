package com.mobile.recorduserapp.service

import com.mobile.recorduserapp.data.response.getusers.GetAllUsers
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface Service{


    @GET("/pupils?page=1")
    suspend  fun getUsers():Response<GetAllUsers>


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

