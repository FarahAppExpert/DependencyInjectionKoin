package com.example.koin.repository

import com.example.koin.apikeys.UserApi


class UserRepository (private val userApi: UserApi)
{
    fun getAllUser () =  userApi.getUser()
}