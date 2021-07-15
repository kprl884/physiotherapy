package com.example.physiotherapy.repository

import android.content.Context
import com.example.physiotherapy.utils.Result
import com.google.firebase.auth.FirebaseUser
import com.example.physiotherapy.model.User
interface UserRepository {
    suspend fun registerUserFromAuthWithEmailAndPassword(
        email: String,
        password: String,
        context: Context?
    ): Result<FirebaseUser?>

    suspend fun createUserInFirestore(user: User): Result<Any?>

    suspend fun logInUserFromAuthWithEmailAndPassword(
        email: String,
        password: String
    ): Result<FirebaseUser?>

    suspend fun getUserFromFirestore(userId: String): Result<Any?>

    suspend fun logOutUser()
}