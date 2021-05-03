package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.physiotherapy.R
import com.example.physiotherapy.foundations.BaseRecyclerAdapter
import com.example.physiotherapy.model.Note
import kotlinx.android.synthetic.main.selected_student_note_recyclerview_item.view.*


class SSNoteAdapter(noteList : MutableList<Note> = mutableListOf()): BaseRecyclerAdapter<Note>(noteList) {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int,
        ): SSTaskViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val lisItem : View = layoutInflater.inflate(R.layout.selected_student_note_recyclerview_item, parent, false)
            return SSTaskViewHolder(lisItem)
        }

    }

    class SSTaskViewHolder(view: View) : BaseRecyclerAdapter.BaseViewHolder<Note>(view){
        override fun onBind(data: Note, position: Int) {
            view.note_item_description.text = data.description
        }
    }