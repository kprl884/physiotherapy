package com.example.physiotherapy.view.students.studentList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.physiotherapy.model.Student

class StudentsViewModel : ViewModel() {


    private val _mutableStudentList: MutableLiveData<MutableList<Student>> = MutableLiveData()
    val mutableStudentList: LiveData<MutableList<Student>> = _mutableStudentList

    init {
        _mutableStudentList.postValue(getFakeData())
    }

    private fun getFakeData(): MutableList<Student> = mutableListOf<Student>(
        Student("Alparslan", "Köprülü", "alprslnk", "123456", 1),
        Student("Bera", "Gelebek", "beraglk", "123456", 2),
        Student("mustafa", "seki", "mstfsk", "123456", 3),
        Student("kedi", "ak", "kdrak", "123456", 4),
        Student("enes", "güreli", "enesgrl", "123456", 5)
    )
}