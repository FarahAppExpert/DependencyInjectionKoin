package com.example.koin.loadingstatus



class Loading private constructor(val status: Status, val msg: String? = null)
{

    /*
Every class can implement a
companion object, which is an object that is common to all instances of that class. ...
And so it is, but due to the way the Android framework instantiate the classes, if you try,
you'll see that the application throws an exception when it's launched.
 */


    companion object
    {
        val LOADED = Loading(Status.SUCCESS)
        val LOADING = Loading(Status.RUNNING)
        fun error(msg: String?) = Loading(Status.FAILED, msg)
    }

    /*
    The most basic usage of enum classes is implementing type-safe enums:
     */

    enum class Status
    {
        RUNNING,
        SUCCESS,
        FAILED
    }

}
