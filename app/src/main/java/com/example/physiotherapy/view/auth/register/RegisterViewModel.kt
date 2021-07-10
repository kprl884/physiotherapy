package com.example.physiotherapy.view.auth.register

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(application: Application) : ViewModel() {

    private var userdata: String? = null

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?>
        get() = _name

    private val _userName = MutableLiveData<String?>()
    val userName: LiveData<String?>
        get() = _userName

    private val _password = MutableLiveData<String?>()
    val password: LiveData<String?>
        get() = _password

    private val _phoneNumber = MutableLiveData<String?>()
    val phoneNumber: LiveData<String?>
        get() = _phoneNumber

    private val _mail = MutableLiveData<String?>()
    val mail: LiveData<String?>
        get() = _mail






}