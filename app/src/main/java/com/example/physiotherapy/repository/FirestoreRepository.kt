package com.example.physiotherapy.repository

import android.content.Context
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.model.User
import com.example.physiotherapy.utils.Result
import com.google.firebase.auth.FirebaseUser

interface FirestoreRepository {
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

    suspend fun checkUserLoggedIn(): FirebaseUser?

    suspend fun sendPasswordResetEmail(
        email: String
    ): Result<Void?>

    suspend fun addNewStudentToFirestore(student: Student): Result<Any?>

    suspend fun getStudentsFromFirestore() : ArrayList<Student>
    
}