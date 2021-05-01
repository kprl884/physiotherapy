package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentNotesBinding
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTaskAdapter
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment


class SelectedStudentNotesFragment : Fragment() {
    //private lateinit var binding: FragmentSelectedStudentTasksBinding
    private lateinit var binding: FragmentSelectedStudentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_student_notes, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectedStudentNotesRecyclerView.layoutManager = LinearLayoutManager(context)
        var noteList = mutableListOf<Note>(Note("notes 1", null),
            Note("notes 2", null))
        val ssNoteAdapter = SSNoteAdapter(noteList)
        binding.selectedStudentNotesRecyclerView.adapter = ssNoteAdapter
    }

    companion object{
        fun newInstance() = SSTasksFragment()
    }
}