package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.view.views.NoteView
import kotlinx.android.synthetic.main.view_add_button.view.*


class SSNoteAdapter(
    noteList: MutableList<Note> = mutableListOf(),
    touchActionDelegate: () -> Unit,
) :
    BaseRecyclerAdapter<Note>(noteList, touchActionDelegate) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder = if (viewType == TYPE_INFO) {
        SSNoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.selected_student_note_recyclerview_item, parent, false)
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
            view.buttonText.text = view.context.getString(R.string.add_new_note)
            view.setOnClickListener {
                if (touchActionDelegate != null) {
                    touchActionDelegate()
                }
            }
        }
    }


    inner class SSNoteViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Note>(view) {
        override fun onBind(
            data: Note,
            position: Int,
            touchActionDelegate: (() -> Unit)?,
            listIndex: Int
        ) {
            (view as NoteView).initView(data)
        }
    }

}

