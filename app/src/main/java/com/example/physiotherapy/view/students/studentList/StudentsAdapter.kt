package com.example.physiotherapy.view.students.studentList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.views.StudentView
import kotlinx.android.synthetic.main.students_recyclerview_item.view.*
import kotlinx.android.synthetic.main.view_add_button.view.*

class StudentsAdapter(
    studentList: MutableList<Student> = mutableListOf(),
    touchActionDelegate: () -> Unit,
    private val touchActionStudentDelegate: ((Student) -> Unit)
) : BaseRecyclerAdapter<Student>(studentList, touchActionDelegate) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder =
        if (viewType == TYPE_INFO) {
            StudentViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.students_recyclerview_item, parent, false)
            )
        } else {
            AddButtonViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_add_button, parent, false)
            )
        }


    inner class AddButtonViewHolder(view: View) : BaseRecyclerAdapter.AddButtonViewHolder(view) {
        override fun onBind(
            data: Unit,
            position: Int,
            touchActionDelegate: (() -> Unit)?,
            listIndex: Int
        ) {
            view.buttonText.text = view.context.getString(R.string.add_new_student)
            view.setOnClickListener {
                if (touchActionDelegate != null) {
                    touchActionDelegate()
                }
            }
        }
    }

    inner class StudentViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Student>(view) {
        override fun onBind(
            data: Student,
            position: Int,
            touchActionDelegate: (() -> Unit)?,
            listIndex: Int
        ) {
            itemView.student_item_tv_name.text = "${data.id} ${data.name} + ${data.surName}"
            itemView.student_item_tv_id.text ="${position+1}"
            itemView.setOnClickListener {
                touchActionStudentDelegate(data)
            }
        }
    }
}


