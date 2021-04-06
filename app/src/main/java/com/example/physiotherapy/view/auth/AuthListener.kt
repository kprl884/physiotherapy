package com.example.physiotherapy.view.auth

interface AuthListener {
    fun onStarted()
    fun onSucces(userName:String, password: String)
    fun onFailure(message: String)
}