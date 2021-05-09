package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import androidx.lifecycle.ViewModel
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.Todo

class SSTaskViewModel : ViewModel() {
    fun getFakeData(): MutableList<Task> = mutableListOf<Task>(
        Task("task 1", mutableListOf(
        Todo("test todo 1", true), Todo("test todo 2"))),
        Task("task 2"),
        Task("task three", mutableListOf(Todo("test todo A"), Todo("test todoB"))))
}