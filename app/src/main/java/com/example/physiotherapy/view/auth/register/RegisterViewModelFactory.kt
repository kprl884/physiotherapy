package com.example.physiotherapy.view.auth.register

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.viewmodel.RegisterViewModel

class RegisterViewModelFactory(

    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel( application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}