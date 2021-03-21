package com.example.koin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.loadingstatus.Loading
import com.example.koin.model.User
import com.example.koin.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel (val userRepository: UserRepository) : ViewModel(), Callback<List<User>>
{
   private val mutableLoadingData = MutableLiveData<Loading>()
   private val loadingData: LiveData<Loading>
   get() = mutableLoadingData


    private val mutableLiveData = MutableLiveData<List<User>>()
    private val UserData: LiveData<List<User>>
    get() = mutableLiveData

    init
    {
        setUserData()
    }

    private fun setUserData ()
    {
        mutableLoadingData.postValue(Loading.LOADED)
        userRepository.getAllUser().enqueue(this)
    }

    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>)
    {
       if (response.isSuccessful)
       {
           mutableLoadingData.postValue(Loading.LOADED)
           mutableLiveData.postValue(response.body())
       }
        else
        {
            mutableLoadingData.postValue(Loading.error(response.errorBody().toString()))
        }
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable)
    {
      mutableLoadingData.postValue(Loading.error(t.message))
    }

}


