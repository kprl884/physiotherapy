package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

interface SSTaskListViewContract {
    fun onTodoUpdated(taskIndex:Int, todoIndex: Int, isComplete: Boolean)
}