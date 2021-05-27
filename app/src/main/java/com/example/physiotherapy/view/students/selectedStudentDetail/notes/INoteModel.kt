package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import com.example.physiotherapy.model.Note

typealias SuccessCallback = (Boolean) -> Unit

interface INoteModel {

    fun addNote(note: Note, callback: SuccessCallback)
    fun updateNote(note: Note, callback: SuccessCallback)
    fun deleteNote(note: Note, callback: SuccessCallback)
    fun retrieveNotes(): List<Note>

    fun getFakeData(): MutableList<Note>
}