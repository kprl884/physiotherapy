package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.physiotherapy.model.Task

class SSTaskViewModel : ViewModel(), SSTaskListViewContract{

    private val model: Taskmodel = Taskmodel()
    private val _taskListLiveData: MutableLiveData<MutableList<Task>> = MutableLiveData()
    val taskListLiveData : LiveData<MutableList<Task>> = _taskListLiveData

    init {
        _taskListLiveData   .postValue(model.getFakeData())
    }

    override fun onTodoUpdated(taskIndex: Int, todoIndex: Int, isComplete: Boolean) {
        _taskListLiveData.value?.get(taskIndex)?.todos?.get(todoIndex)?.isComplete = isComplete
    }
}