package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.selected_student_task_recyclerview_item.view.*
import kotlinx.android.synthetic.main.selected_student_view_todo.view.*

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
        view.ss_task_textView.text = data.title

        data.todos.forEach { todo ->
            val todoView =
                LayoutInflater.from(view.context).inflate(R.layout.selected_student_view_todo,
                    view.task_item_todo_container, false).apply {
                    ss_description_view.text = todo.description
                    ss_complete_checkbox.isChecked = todo.isComplete
                    if (todo.isComplete){
                        ss_description_view.paintFlags = ss_description_view.paintFlags or
                                Paint.STRIKE_THRU_TEXT_FLAG
                    }
                    ss_complete_checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                        if (isChecked){
                            ss_description_view.paintFlags = ss_description_view.paintFlags or
                                    Paint.STRIKE_THRU_TEXT_FLAG
                        }  else {
                            ss_description_view.paintFlags = ss_description_view.paintFlags and
                                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
                        }
                    }
                }

            view.task_item_todo_container.addView(todoView)

        }
    }
}




