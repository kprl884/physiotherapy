package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import androidx.lifecycle.ViewModel
import com.example.physiotherapy.model.Note

class SSNoteViewModel : ViewModel() {
    fun getFakeData(): MutableList<Note> = mutableListOf<Note>(
        Note("notes 1", null),
        Note("notes 2", null),
        Note("notes 3", null))


}