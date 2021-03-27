package com.example.physiotherapy.view.auth

interface AuthListener {
    fun onStarted()
    fun onSucces()
    fun onFailure(message: String)
}