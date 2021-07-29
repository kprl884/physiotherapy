package com.example.physiotherapy.repository

import android.content.Context
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.User
import com.example.physiotherapy.utils.Result
import com.google.firebase.auth.FirebaseUser

interface FirestoreRepository {
    // Auth Repository
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

    //Student get set Repository
    suspend fun addNewStudentToFirestore(student: Student): Result<Any?>

    suspend fun getStudentsFromFirestore() : ArrayList<Student>

    //Create Repository

    suspend fun addNewNoteToFirestore(note: Note,  studentId : String) : Result<Any?>

    suspend fun addNewTaskToFirestore(task: Task,  studentId : String) : Result<Any?>

    suspend fun getNotesFromFirestore(studentId: String) : Result<Any?>

    suspend fun getTasksFromFirestore(studentId: String) : Result<Any?>


    
}