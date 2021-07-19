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
        Student("Alparslan", "1", "Köprülü"),
        Student("Bera", "2", "Gelebek"),
        Student("mustafa", "3", "seki"),
        Student("kedi", "4", "ak"),
        Student("enes", "5", "güreli")
    )
}