package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentNotesBinding
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTaskAdapter
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment


class SSNotesFragment : Fragment() {
    //private lateinit var binding: FragmentSelectedStudentTasksBinding
    private lateinit var binding: FragmentSelectedStudentNotesBinding
    lateinit var noteViewModel: SSNoteViewModel
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

        bindViewModel()

        binding.selectedStudentNotesRecyclerView.layoutManager = LinearLayoutManager(context)
        var noteList = noteViewModel.getFakeData()
        val ssNoteAdapter = SSNoteAdapter(noteList)
        binding.selectedStudentNotesRecyclerView.adapter = ssNoteAdapter
    }

    private fun bindViewModel() {
        noteViewModel = ViewModelProvider(this).get(SSNoteViewModel::class.java)
    }

    companion object{
        fun newInstance() = SSTasksFragment()
    }
}