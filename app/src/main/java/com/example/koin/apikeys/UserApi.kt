package com.example.koin.apikeys

import com.example.koin.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApi
{
    @GET("User")
    fun getUser () : Call<List<User>>
}

