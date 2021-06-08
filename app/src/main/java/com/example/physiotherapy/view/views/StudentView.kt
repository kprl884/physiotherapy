package com.example.physiotherapy.view.views

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Student

class StudentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    lateinit var student: Student
    private var studentInformation: TextView? = null
    fun initView(student: Student, studentCallback: (Student) -> Unit) {
       this.student = student
        studentInformation = findViewById(R.id.student_item_tv_name)
        studentInformation!!.text = "${student.id} ${student.name} + ${student.surName}"
        studentCallback.invoke(student)
    }


}