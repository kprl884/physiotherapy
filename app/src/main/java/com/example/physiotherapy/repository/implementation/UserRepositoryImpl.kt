package com.example.physiotherapy.repository.implementation

import android.content.Context
import android.util.Log
import com.example.physiotherapy.extension.await
import com.example.physiotherapy.model.User
import com.example.physiotherapy.repository.FireabaseConst.Companion.USER_COLLECTION_NAME
import com.example.physiotherapy.repository.UserRepository
import com.example.physiotherapy.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

private val TAG = "UserRepositoryImpl"

class UserRepositoryImpl : UserRepository {

    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val userCollection = firestoreInstance.collection(USER_COLLECTION_NAME)
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override suspend fun registerUserFromAuthWithEmailAndPassword(
        email: String,
        password: String,
        context: Context?
    ): Result<FirebaseUser?> {
        try {
            return when (
                val resultDocumentSnapshot = firebaseAuth.createUserWithEmailAndPassword(
                    email, password
                ).await()
            ) {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data.user
                    Result.Success(firebaseUser)
                }
                is Result.Error -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Error(resultDocumentSnapshot.exception)
                }
                is Result.Canceled -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Canceled(resultDocumentSnapshot.exception)
                }
            }
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
    }

    override suspend fun createUserInFirestore(user: com.example.physiotherapy.model.User): Result<Any?> {
        return try {
            userCollection.document(user.id).set(user).await()
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
    }

    override suspend fun logInUserFromAuthWithEmailAndPassword(
        email: String,
        password: String
    ): Result<FirebaseUser?> {
        try {
            return when (val resultDocumentSnapshot =
                firebaseAuth.signInWithEmailAndPassword(email, password).await()) {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data.user
                    Result.Success(firebaseUser)
                }
                is Result.Error -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Error(resultDocumentSnapshot.exception)
                }
                is Result.Canceled -> {
                    Log.e(TAG, "${resultDocumentSnapshot.exception}")
                    Result.Canceled(resultDocumentSnapshot.exception)
                }
            }
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
    }

    override suspend fun getUserFromFirestore(userId: String): Result<Any?> {
        try
        {
            return when(val resultDocumentSnapshot = userCollection.document(userId).get().await())
            {
                is Result.Success -> {
                    val user = resultDocumentSnapshot.data.toObject(User::class.java)!!
                    Result.Success(user)
                }
                is Result.Error -> Result.Error(resultDocumentSnapshot.exception)
                is Result.Canceled -> Result.Canceled(resultDocumentSnapshot.exception)
            }
        }
        catch (exception: Exception)
        {
            return Result.Error(exception)
        }
    }

    override suspend fun logOutUser() {
        try {
            firebaseAuth.signOut()
        }catch (exception : Exception) {
             Result.Error(exception)
        }
    }
}