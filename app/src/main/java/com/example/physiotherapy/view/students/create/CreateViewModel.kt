package com.example.physiotherapy.view.students.create

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.User
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.repository.implementation.FirestoreRepositoryImpl
import com.example.physiotherapy.utils.Result
import com.example.physiotherapy.view.auth.login.LoginFragment
import kotlinx.coroutines.launch

private val TAG = "CreateViewModel"

class CreateViewModel : ViewModel() {
    private val firestoreRepository: FirestoreRepository = FirestoreRepositoryImpl()

    fun addNewNote(note: Note, studentId: String) {
        viewModelScope.launch {
            when (val result = firestoreRepository.addNewNoteToFirestore(note, studentId)) {
                is Result.Success -> {
                    Log.d(TAG, "Result.Error - ${note.description}")
                    //_currentUserMLD.value = user
                    //startMainActivitiy(activity)
                }
                is Result.Error -> {
                    //_toast.value = result.exception.message
                }
                is Result.Canceled -> {
                    //toast.value = activity.getString(R.string.request_canceled)
                }
            }
        }
    }


}