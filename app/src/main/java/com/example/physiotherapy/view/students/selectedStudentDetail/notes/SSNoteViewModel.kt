package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.view.students.create.CreateNoteFragment
import com.example.physiotherapy.view.students.create.CreateNoteFragmentArgs

class SSNoteViewModel : ViewModel(), SSNoteListViewContract {

    private val model: NoteModel = NoteModel()

    private val _mutableNoteList : MutableLiveData<MutableList<Note>> = MutableLiveData()
    val mutableNoteList: LiveData<MutableList<Note>> = _mutableNoteList

    init {
        _mutableNoteList.postValue(model.getFakeData())
    }




}