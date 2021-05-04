package com.example.physiotherapy.view.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.selected_student_task_recyclerview_item.view.*
import kotlinx.android.synthetic.main.selected_student_view_todo.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStlyeAttr: Int = 1,
) : ConstraintLayout(context, attrs, defStlyeAttr)  {
    lateinit var task : Task
    fun initView (task: Task){
        this.task = task
        ss_task_textView.text = task.title
        task.todos.forEach {todo->
            val todoView = (LayoutInflater.from(context).inflate(R.layout.selected_student_view_todo,
                task_item_todo_container, false) as TodoView).apply {
                initView(todo) {
                    if(isTaskComplete()){
                        createStrikeThrough()
                    }else {
                        removeStrikeThrough()
                    }
                }
            }
            task_item_todo_container.addView(todoView)
        }
    }

    private fun isTaskComplete() :Boolean = task.todos. filter {!it.isComplete }.isEmpty()

    private fun removeStrikeThrough() {
        ss_task_textView.apply {
            paintFlags = paintFlags and
                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    private fun createStrikeThrough() {
        ss_task_textView.apply {
            paintFlags = paintFlags or
                    Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

}