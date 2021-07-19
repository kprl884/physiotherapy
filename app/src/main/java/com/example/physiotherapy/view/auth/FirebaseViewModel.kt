package com.example.physiotherapy.view.auth

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physiotherapy.R
import com.example.physiotherapy.model.User
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.repository.implementation.FirestoreRepositoryImpl
import com.example.physiotherapy.utils.Result
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.example.physiotherapy.view.home.HomeFragment
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

private val TAG = "FirebaseViewModel"

class FirebaseViewModel : ViewModel() {
    private val _currentUserMLD = MutableLiveData<User>(User())
    val currentUserLD: LiveData<User>
        get() = _currentUserMLD

    val firestoreRepository: FirestoreRepository = FirestoreRepositoryImpl()

    //Email
    fun registerUserFromAuthWithEmailAndPassword(
        name: String, phoneNumber: String, email: String, password: String, activity: Activity
    ) {

        viewModelScope.launch {
            when (val result = firestoreRepository.registerUserFromAuthWithEmailAndPassword(
                email,
                password,
                activity.applicationContext
            )) {
                is Result.Success -> {

                    Log.e(TAG, "Result.Success")
                    result.data?.let { firebaseUser ->
                        createUserInFirestore(
                            createUserObject(
                                firebaseUser, name, phoneNumber = phoneNumber,
                                mail = email, password = password
                            ), activity
                        )
                    }
                    openFragment(LoginFragment.newInstance(), activity as FragmentActivity)
                    /*
                    activity.findNavController(R.id.navigation_fragment).navigate(
                        R.id.action_registerFragment_to_loginFragment2,
                        null,
                    )
                    */

                }
                is Result.Error -> {
                    Log.e(TAG, "${result.exception.message}")
                    //_toast.value = result.exception.message
                }
                is Result.Canceled -> {
                    Log.e(TAG, "${result.exception!!.message}")
                    //_toast.value = activity.getString(R.string.request_canceled)
                }
            }
        }

    }

    // TODO : toast mesaages will design
    private fun openFragment(fragment: Fragment, activity: FragmentActivity) {
        Log.d("alp", "open fragment in viewmdel")
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.commit()
    }

    private suspend fun createUserInFirestore(user: User, activity: Activity) {
        Log.d(TAG, "Result - ${user.name}")
        when (val result = firestoreRepository.createUserInFirestore(user)) {
            is Result.Success -> {
                Log.d(TAG, "Result.Error - ${user.name}")
                _currentUserMLD.value = user
                //startMainActivitiy(activity)
            }
            is Result.Error -> {
                //_toast.value = result.exception.message
            }
            is Result.Canceled -> {
                //_toast.value = activity.getString(R.string.request_canceled)
            }
        }
    }

    fun createUserObject(
        firebaseUser: FirebaseUser,
        name: String,
        phoneNumber: String,
        mail: String,
        password: String
    ): User {
        return User(
            id = firebaseUser.uid,
            name = name,
            phoneNumber = phoneNumber,
            mail = mail,
            password = password
        )
    }

    fun logInUserFromAuthWithEmailAndPassword(email: String, password: String, activity: Activity) {
        viewModelScope.launch {
            when (val result =
                firestoreRepository.logInUserFromAuthWithEmailAndPassword(email, password)) {
                is Result.Success -> {
                    Log.i(TAG, "SignIn - Result.Success - User: ${result.data}")
                    result.data?.let { firebaseUser ->
                        // _toast.value = activity.getString(R.string.login_successful)
                        getUserFromFirestore(firebaseUser.uid, activity)
                    }
                    openFragment(HomeFragment.newInstance(), activity as FragmentActivity)
                }
                is Result.Error -> {
                    //_toast.value = result.exception.message
                }
                is Result.Canceled -> {
                    //_toast.value = activity.getString(R.string.request_canceled)
                }
            }
        }

    }

    suspend fun getUserFromFirestore(userId: String, activity: Activity) {
        when (val result = firestoreRepository.getUserFromFirestore(userId)) {
            is Result.Success -> {
                val _user = result.data
                _currentUserMLD.value = _user as User
                //startMainActivitiy(activity = activity)
            }
            is Result.Error -> {
                //_toast.value = result.exception.message
            }
            is Result.Canceled -> {
                //_toast.value = activity.getString(R.string.request_canceled)
            }
        }
    }

    fun checkUserLoggedIn(): FirebaseUser? {
        var _firebaseUser: FirebaseUser? = null
        viewModelScope.launch {
            _firebaseUser = firestoreRepository.checkUserLoggedIn()
        }
        return _firebaseUser
    }

    fun logOutUser() {
        viewModelScope.launch {
            firestoreRepository.logOutUser()
        }
    }

    fun sendPasswordResetEmail(email: String, activity: Activity) {
        viewModelScope.launch {
            when (val result = firestoreRepository.sendPasswordResetEmail(email)) {
                is Result.Success -> {
                    //Toast.makeText(activity, "Check email to reset your password!", Toast.LENGTH_SHORT).show()
                }
                is Result.Error -> {
                    //_toast.value = result.exception.message
                }
                is Result.Canceled -> {
                    // _toast.value = activity.getString(R.string.request_canceled)
                }
            }
        }
    }
}