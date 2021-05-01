package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.physiotherapy.R
import com.example.physiotherapy.model.Note
import kotlinx.android.synthetic.main.selected_student_note_recyclerview_item.view.*

class SSNoteAdapter(private val noteList : MutableList<Note> = mutableListOf()): RecyclerView.Adapter<SSNoteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SSNoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lisItem : View = layoutInflater.inflate(R.layout.selected_student_note_recyclerview_item, parent, false)
        return SSNoteViewHolder(lisItem)
    }

    override fun onBindViewHolder(
        holder: SSNoteViewHolder,
        position: Int,
    ) {
        holder.onBind(noteList[position], position)
    }

    override fun getItemCount(): Int = noteList.size
}

class SSNoteViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun onBind(note: Note, position: Int) {
        view.note_rv_item_note_name.text = note.description
        view.note_rv_item_note_id.text = position.toString()
    }
}