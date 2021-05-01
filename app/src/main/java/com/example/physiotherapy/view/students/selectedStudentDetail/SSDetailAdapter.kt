package com.example.physiotherapy.view.students.selectedStudentDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.physiotherapy.R
import com.example.physiotherapy.model.Task
import kotlinx.android.synthetic.main.selected_student_detail_item.view.*

class SelectedStudentDetailAdapter(
    private val tasklist : MutableList<Task> = mutableListOf()
) : RecyclerView.Adapter<SelectedStudentDetailViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SelectedStudentDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem : View = layoutInflater.inflate(R.layout.selected_student_detail_item, parent, false)
        return SelectedStudentDetailViewHolder(lisItem)
    }

    override fun onBindViewHolder(
        holder: SelectedStudentDetailViewHolder,
        position: Int,
    ) {
        holder.onBind(tasklist[position])
    }

    override fun getItemCount(): Int = tasklist.size
}

class SelectedStudentDetailViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun onBind(task: Task ) {
        view.task_title.text = task.title
    }
}