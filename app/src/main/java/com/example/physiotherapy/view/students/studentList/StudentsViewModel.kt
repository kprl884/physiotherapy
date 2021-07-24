package com.example.physiotherapy.view.students.studentList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.repository.FirestoreRepository
import com.example.physiotherapy.repository.implementation.FirestoreRepositoryImpl
import com.example.physiotherapy.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class StudentsViewModel : ViewModel() {
    private val firestoreRepository: FirestoreRepository = FirestoreRepositoryImpl()


    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    val mutableData = MutableLiveData<ArrayList<Student>>().apply {
        viewModelScope.launch(Dispatchers.IO){
            val result =firestoreRepository.getStudentsFromFirestore()
            postValue(result)
        }
    }
}