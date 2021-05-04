package com.example.physiotherapy.view.views

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Note

class NoteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,

    ) : ConstraintLayout(context, attrs, defStyleAttr)  {
        private var descriptionView: EditText? = null
        fun initView (note : Note){
            descriptionView = findViewById(R.id.note_item_description)
            descriptionView!!.text = note.description.toEditable()
        }

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
}