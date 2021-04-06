package com.example.physiotherapy.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.physiotherapy.view.auth.AuthListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AuthViewModel : ViewModel() {
    var email:String? = null
    var userName:String? = null
    var password:String? = null
    val currentUser = Firebase.auth.currentUser
    val db = Firebase.firestore

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view : View){

        /*
        authListener?.onStarted()
        checkUserNamePassword()
        if(userName.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("Invalid email or password")
            return
        }
        userName?.let { authListener?.onSucces(it, password!!) }
        //succes

         */
    }


    fun checkUserNamePassword(){
        val docRef = db.collection("users").document("$currentUser")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed.", e)
                return@addSnapshotListener
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                "Local"
            else
                "Server"

            if (snapshot != null && snapshot.exists()) {
                Log.d("TAG", "$source data: ${snapshot.data}")
            } else {
                Log.d("TAG", "$source data: null")
            }
        }
    }
}