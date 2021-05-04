package com.example.physiotherapy.view.views

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.model.Note
import kotlinx.android.synthetic.main.selected_student_view_todo.view.*

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStlyeAttr: Int = 1,

    ) : ConstraintLayout(context, attrs, defStlyeAttr)  {
        fun initView (note : Note){
            ss_description_view.text = note.description
        }
}