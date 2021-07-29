package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import com.example.physiotherapy.model.Note

class NoteModel : INoteModel {
    override fun addNote(note: Note, callback: SuccessCallback) {
        callback.invoke(true)
    }

    override fun updateNote(note: Note, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: Note, callback: SuccessCallback) {
        TODO("Not yet implemented")
    }

    override fun retrieveNotes(): List<Note> {
        TODO("Not yet implemented")
    }

    override fun getFakeData(): MutableList<Note> = mutableListOf<Note>(
        Note("notes 1"),
        Note("notes 2"),
        Note("notes 3")
    )
}