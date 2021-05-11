package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.model.Note
import kotlinx.android.synthetic.main.fragment_selected_student_notes.view.*

class SSNoteListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var adapter: SSNoteAdapter
    private lateinit var dataActionDelegate: SSNoteListViewContract
    private lateinit var touchActionDelegate: () -> Unit
    fun initView(touchActionDelegate: () -> Unit, dataDelegate: SSNoteListViewContract) {
        setTouchFunc(touchActionDelegate, dataDelegate)
        setUpView()
    }

    private fun setUpView() {
        selected_student_notes_recycler_view.layoutManager = LinearLayoutManager(context)
        adapter = SSNoteAdapter(
            touchActionDelegate = touchActionDelegate
        )
        selected_student_notes_recycler_view.adapter = adapter
    }

    private fun setTouchFunc(touchDelegate: () -> Unit, dataDelegate: SSNoteListViewContract) {
        touchActionDelegate = touchDelegate
        dataActionDelegate = dataDelegate
    }

    fun updateList(list: MutableList<Note>){
        adapter.updateList(list)
    }
}