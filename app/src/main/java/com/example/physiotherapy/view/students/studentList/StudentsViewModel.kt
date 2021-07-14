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
        Student(1,"Alparslan", "Köprülü"),
        Student(2,"Bera", "Gelebek"),
        Student(3,"mustafa", "seki"),
        Student(4,"kedi", "ak"),
        Student(5,"enes", "güreli")
    )
}