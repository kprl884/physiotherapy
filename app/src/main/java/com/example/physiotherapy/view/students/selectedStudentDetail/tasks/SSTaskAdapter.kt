package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.selected_student_task_recyclerview_item.view.*

class SSTaskAdapter(private val tasklist : MutableList<Task> = mutableListOf()): RecyclerView.Adapter<SSTaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SSTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem : View = layoutInflater.inflate(R.layout.selected_student_task_recyclerview_item, parent, false)
        return SSTaskViewHolder(lisItem)
    }

    override fun onBindViewHolder(
        holder: SSTaskViewHolder,
        position: Int,
    ) {
        holder.onBind(tasklist[position], position)
    }

    override fun getItemCount(): Int = tasklist.size
}

class SSTaskViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun onBind(task: Task, position: Int) {
        view.task_rv_item_task_name.text = task.title
        view.task_rv_item_task_id.text = position.toString()
    }
}




