package com.example.physiotherapy.view.students.studentList.addStudent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.repository.implementation.FirestoreRepositoryImpl
import com.example.physiotherapy.utils.Result
import kotlinx.coroutines.launch

private val TAG = "FirebaseViewModel"

class AddStudentViewModel : ViewModel() {
    private val _currentStudentMLD = MutableLiveData<Student>(Student())
    val currentStudentLD: LiveData<Student>
        get() = _currentStudentMLD


    private val _currentResultMLD = MutableLiveData<Result<Student>>()
    val currentResultLD: LiveData<Result<Student>>
        get() = _currentResultMLD

    val firestoreRepository: FirestoreRepository = FirestoreRepositoryImpl()

    fun addNewStudentToFireStore(student: Student) {
        viewModelScope.launch {
            when (val result = firestoreRepository.addNewStudentToFirestore(student)) {
                is Result.Success -> {
                    Log.e(TAG, "Result.Success")
                    _currentResultMLD.value = Result.Success<Student>(student)
                    //ToastMessage
                }
                is Result.Error -> {
                    Log.e(TAG, "${result.exception.message}")
                    _currentResultMLD.value = Result.Error(exception = result.exception)
                    //_toast.value = result.exception.message
                }
                is Result.Canceled -> {
                    Log.e(TAG, "${result.exception!!.message}")
                    _currentResultMLD.value = Result.Canceled(exception = result.exception)
                    //_toast.value = activity.getString(R.string.request_canceled)
                }
            }
        }
    }
}