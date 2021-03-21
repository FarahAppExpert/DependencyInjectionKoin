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


class ViewModel(private val repo: UserRepository) : ViewModel(), Callback<List<User>> {

    private val _loadingState = MutableLiveData<Loading>()
    val loadingState: LiveData<Loading>
        get() = _loadingState

    private val _data = MutableLiveData<List<User>>()
    val data: LiveData<List<User>>
        get() = _data

    init {
        fetchData()
    }

    private fun fetchData() {
        _loadingState.postValue(Loading.LOADED)
        repo.getAllUser().enqueue(this)
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {
        _loadingState.postValue(Loading.error(t.message))
    }

    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        if (response.isSuccessful) {
            _data.postValue(response.body())
            _loadingState.postValue(Loading.LOADED)
        } else {
            _loadingState.postValue(Loading.error(response.errorBody().toString()))
        }
    }
}