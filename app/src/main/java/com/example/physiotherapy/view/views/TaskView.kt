package com.example.physiotherapy.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.selected_student_task_recyclerview_item.view.*

class TaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    lateinit var task: Task
    fun initView(task: Task, todoIndexCallback: (Int, Boolean) -> Unit) {
        this.task = task
        ss_task_textView.text = task.title
        task.todos.forEachIndexed { index, todo ->
            val todoView = (LayoutInflater.from(context).inflate(
                R.layout.selected_student_view_todo,
                task_item_todo_container, false
            ) as TodoView).apply {
                initView(todo) { isChecked ->
                    todoIndexCallback.invoke(index, isChecked)
                    if (isTaskComplete()) {
                        this@TaskView.ss_task_textView.setStrikeThrough()
                    } else {
                        this@TaskView.ss_task_textView.removeStrikeThrough()
                    }
                }
            }
            task_item_todo_container.addView(todoView)
        }
    }

    private fun isTaskComplete(): Boolean = task.todos.filter { !it.isComplete }.isEmpty()

}