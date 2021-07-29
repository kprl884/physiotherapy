package com.example.physiotherapy.view.students.selectedStudentDetail.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentNotesBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment


class SSNotesFragment(val selectedStudentObject: Student?) : BaseFragment() {
    private lateinit var binding: FragmentSelectedStudentNotesBinding
    private lateinit var noteViewModel: SSNoteViewModel
    lateinit var contentView: SSNoteListView
    private var newNote: Note? = null
    private val TAG: String = SSNotesFragment::class.java.simpleName

    //private val args: SSNotesFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_selected_student_notes, container, false).apply {
            contentView = this as SSNoteListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newNote = arguments?.getParcelable("newNote")
        bindViewModel()
        setContentView()
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE, TAG)

    }

    private fun setContentView() {
        contentView.initView({ onAddButtonClicked() }, noteViewModel)
    }

    private fun bindViewModel() {
        noteViewModel = ViewModelProvider(this).get(SSNoteViewModel::class.java)
        if (selectedStudentObject != null) {
            noteViewModel.getNotesFromFirestore(selectedStudentObject.id)
        }
        noteViewModel.currentNoteListLD.observe(viewLifecycleOwner, Observer { noteList ->

            contentView.updateList(noteList)
        })
    }

    private fun onAddButtonClicked() {
        val bundleSSDetail =
            bundleOf("studentSSDetail" to selectedStudentObject)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_selectedStudentFragment_to_createNoteFragment,
            bundleSSDetail,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    companion object {
        fun newInstance() = SSTasksFragment()
    }
}