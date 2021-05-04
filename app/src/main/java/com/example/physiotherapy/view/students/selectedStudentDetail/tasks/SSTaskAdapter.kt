package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.views.TaskView
import com.example.physiotherapy.view.views.TodoView
import kotlinx.android.synthetic.main.selected_student_task_recyclerview_item.view.*

class SSTaskAdapter(taskList: MutableList<Task> = mutableListOf()) :
    BaseRecyclerAdapter<Task>(taskList) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SSTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem: View =
            layoutInflater.inflate(R.layout.selected_student_task_recyclerview_item, parent, false)
        return SSTaskViewHolder(lisItem)
    }
}

class SSTaskViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Task>(view) {
    override fun onBind(data: Task, position: Int) {
        (view as TaskView).initView(data)
    }
}




