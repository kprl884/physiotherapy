package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import com.example.physiotherapy.model.Note

class NoteModel {

    fun getFakeData(): MutableList<Note> = mutableListOf<Note>(
        Note("notes 1", null),
        Note("notes 2", null),
        Note("notes 3", null)
    )
}