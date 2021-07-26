package com.example.physiotherapy.repository.implementation

import android.content.Context
import android.util.Log
import com.example.physiotherapy.extension.await
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.User
import com.example.physiotherapy.repository.FireabaseConst.Companion.STUDENT_COLLECTION_NAME
import com.example.physiotherapy.repository.FireabaseConst.Companion.USER_COLLECTION_NAME
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val TAG = "AuthRepositoryImpl"

class FirestoreRepositoryImpl : FirestoreRepository {

    // Auth Repository
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val userCollection = firestoreInstance.collection(USER_COLLECTION_NAME)
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
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

    override suspend fun createUserInFirestore(user: User): Result<Any?> {
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
        return try {
            when (val resultDocumentSnapshot =
                userCollection.document(userId).get().await()) {
                is Result.Success -> {
                    val user = resultDocumentSnapshot.data.toObject(User::class.java)!!
                    Result.Success(user)
                }
                is Result.Error -> Result.Error(resultDocumentSnapshot.exception)
                is Result.Canceled -> Result.Canceled(resultDocumentSnapshot.exception)
            }
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun logOutUser() {
        try {
            firebaseAuth.signOut()
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun checkUserLoggedIn(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<Void?> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    //Student get set Repository
    override suspend fun addNewStudentToFirestore(student: Student): Result<Any?> {
        try {
            return when (val resultDocumentSnapshot =
                db.collection(STUDENT_COLLECTION_NAME).document(student.id).set(student)
                    .await()) {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data
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

    override suspend fun getStudentsFromFirestore(): ArrayList<Student> {
        val studentArrayList: ArrayList<Student> = arrayListOf()
        var studentHashMap: HashMap<String, *>
        return suspendCoroutine { cont ->
            db.collection(STUDENT_COLLECTION_NAME)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        studentArrayList.add(document.toObject(Student::class.java))
                        Log.i(TAG, "${document.id} => ${document.data}")
                    }
                    try {
                        cont.resume(studentArrayList)
                    } catch (exception: Exception) {
                        Log.d(TAG, exception.localizedMessage)
                        Result.Error(exception)
                    }
                }

        }
    }

    //Create Repository
    override suspend fun addNewNoteToFirestore(note: Note,  studentId : String): Result<Any?> {
        try {
            return when (val resultDocumentSnapshot =
                db.collection(STUDENT_COLLECTION_NAME).document(studentId).collection("notes").add(note)
                    .await()) {
                is Result.Success -> {
                    Log.i(TAG, "Result.Success")
                    val firebaseUser = resultDocumentSnapshot.data
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

    override suspend fun addNewTaskToFirestore(task: Task,  studentId : String): Result<Any?> {
        TODO("Not yet implemented")
    }
}

