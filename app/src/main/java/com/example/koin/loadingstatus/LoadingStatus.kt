package com.example.koin.loadingstatus


import com.example.koin.model.User
import org.koin.core.context.startKoin

class LoadingStatus private constructor(val status: Status, val string: String? = null)
{
    companion object
    {
        val LOADED = LoadingStatus(Status.SUCCESS)
        val LOADING = LoadingStatus(Status.RUNNING)
        fun ErrorMessage (message : String?) = (Status.FAILED)

    }

    fun setKoin (message : String, status: Status)
    {
        startKoin {
             Status.SUCCESS
               Status.RUNNING
               Status.FAILED

            status.declaringClass

            sequence<User>
            {

                Status.SUCCESS
                Status.RUNNING
                Status.FAILED
            }
        }


    }

    enum class Status ()
    {
         SUCCESS,
          RUNNING,
          FAILED
    }
}