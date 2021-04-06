package com.example.physiotherapy.view.students

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Student
import kotlinx.android.synthetic.main.students_recyclerview_item.view.*


class StudentsFragmentAdapter(private val studentList: List<Student>, private val editClickListener:(Student)->Unit,
                              private val deleteClickListener:(Student)->Unit,
                              private val selectedStudentDetailListener:(Student) -> Unit
                              ) : RecyclerView.Adapter<StudentsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem : View = layoutInflater.inflate(R.layout.students_recyclerview_item, parent, false)
        return StudentsViewHolder(lisItem)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        holder.bind(studentList[position], editClickListener, deleteClickListener, selectedStudentDetailListener)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}

class StudentsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(student: Student,
             editClickListener: (Student) -> Unit,
             deleteClickListener: (Student) -> Unit,
             selectedStudentDetailListener: (Student) -> Unit
    ){
        itemView.student_item_tv_name.text ="""${student.name} ${student.surName}"""
        itemView.student_item_tv_id.text = student.id.toString()
        itemView.student_item_tv_name.setOnClickListener {
            selectedStudentDetailListener(student)
        }
        itemView.student_item_ib_edit.setOnClickListener {
            editClickListener(student)
        }
        itemView.student_item_ib_delete.setOnClickListener {
            deleteClickListener(student)
        }

    }
}