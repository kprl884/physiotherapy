package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.repository.implementation.FirestoreRepositoryImpl
import com.example.physiotherapy.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
private val TAG = "SSNoteViewModel"
class SSNoteViewModel : ViewModel(), SSNoteListViewContract {

    private val _currentNoteListMLD = MutableLiveData<ArrayList<Note>>(ArrayList<Note>())
    val currentNoteListLD: LiveData<ArrayList<Note>>
        get() = _currentNoteListMLD



    private val firestoreRepository: FirestoreRepository = FirestoreRepositoryImpl()

    fun getNotesFromFirestore(studentId: String) {
        viewModelScope.launch {
            firestoreRepository.getNotesFromFirestore(studentId)
            when (val result = firestoreRepository.getNotesFromFirestore(studentId)) {
                is Result.Success -> {
                    Log.e(TAG, "Result.Success")
                    _currentNoteListMLD.value = (result.data as ArrayList<Note>?)!!
                }
                is Result.Error -> {
                    Log.e(TAG, "${result.exception.message}")
                }
                is Result.Canceled -> {
                    Log.e(TAG, "${result.exception!!.message}")
                }
            }
        }
    }
}