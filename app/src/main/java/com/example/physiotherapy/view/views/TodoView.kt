package com.example.physiotherapy.view.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Todo
import kotlinx.android.synthetic.main.selected_student_view_todo.view.*


class TodoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStlyeAttr: Int = 1,
    ) : ConstraintLayout(context, attrs, defStlyeAttr) {
    private var descriptionView: TextView? = null
    private var checkBox: CheckBox? = null

    fun initView(todo: Todo, callback: (() -> Unit)? = null) {
        descriptionView = findViewById(R.id.ss_description_view)
        checkBox = findViewById(R.id.ss_complete_checkbox)
        descriptionView!!.text = todo.description
        checkBox!!.isChecked = todo.isComplete
        if (todo.isComplete) {
            createStrikeThrough()
        }
        setUpCheckStateListener(todo, callback)
    }

    private fun setUpCheckStateListener(todo:Todo, callback: (() -> Unit)? = null) {
        checkBox?.setOnCheckedChangeListener { _, isChecked ->
            todo.isComplete = isChecked
            callback?.invoke()
            if (isChecked) {
                createStrikeThrough()
            } else {
                removeStrikeThrough()
            }
        }
    }

    private fun removeStrikeThrough() {
        ss_description_view.apply {
            paintFlags = paintFlags and
                    Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    private fun createStrikeThrough() {
        ss_description_view.apply {
            paintFlags = paintFlags or
                    Paint.STRIKE_THRU_TEXT_FLAG
        }
    }


}