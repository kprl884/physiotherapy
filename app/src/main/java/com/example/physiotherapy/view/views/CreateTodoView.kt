package com.example.physiotherapy.view.views

import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import android.content.Context
import com.example.physiotherapy.foundations.NullFieldChecker
import kotlinx.android.synthetic.main.view_create_todo.view.*

class CreateTodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
): ConstraintLayout(context, attrs, defStyleAttr), NullFieldChecker {

    override fun hasNullField(): Boolean = todoEditText.editableText.isNullOrEmpty()
}