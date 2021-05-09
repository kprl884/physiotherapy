package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.views.TaskView
import kotlinx.android.synthetic.main.view_add_button.view.*

class SSTaskAdapter(
    taskList: MutableList<Task> = mutableListOf(),
    touchActionDelegate: () -> Unit,
) :
    BaseRecyclerAdapter<Task>(taskList, touchActionDelegate) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            SSTaskViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.selected_student_task_recyclerview_item, parent, false))
        } else {
            AddButtonViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_add_button, parent, false))
        }

    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(data: Unit, position: Int, touchActionDelegate: (() -> Unit)?) {
            view.buttonText.text = view.context.getString(R.string.add_new_task)
            view.setOnClickListener {
                if (touchActionDelegate != null) {
                    touchActionDelegate()
                }
            }
        }
    }

    class SSTaskViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Task>(view) {
        override fun onBind(data: Task, position: Int, touchActionDelegate: (() -> Unit)?) {
            (view as TaskView).initView(data)
        }
    }

}








