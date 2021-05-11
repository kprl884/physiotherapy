package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentNotesBinding
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTaskListView
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment


class SSNotesFragment : Fragment() {
    private lateinit var binding: FragmentSelectedStudentNotesBinding
    lateinit var noteViewModel: SSNoteViewModel
    lateinit var contentView: SSNoteListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        /*
            binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_selected_student_notes,
            container,
            false
        )
        val inflater1 = inflater.inflate(R.layout.fragment_selected_student_notes, container, false)
        inflater1.apply {
            contentView = this as SSNoteListView
        }
        return binding.root
         */

        return inflater.inflate(R.layout.fragment_selected_student_notes, container, false).apply {
            contentView = this as SSNoteListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()
        setContentView()
    }

    private fun setContentView() {
        contentView.initView ({ onAddButtonClicked()  }, noteViewModel)
    }

    private fun bindViewModel() {
        noteViewModel = ViewModelProvider(this).get(SSNoteViewModel::class.java)

        noteViewModel.mutableNoteList.observe(viewLifecycleOwner, Observer { noteList ->
            contentView.updateList(noteList)
        })
    }

    private fun onAddButtonClicked() {

    }

    companion object {
        fun newInstance() = SSTasksFragment()
    }
}