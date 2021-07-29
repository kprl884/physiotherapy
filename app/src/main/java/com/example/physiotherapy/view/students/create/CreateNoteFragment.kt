package com.example.physiotherapy.view.students.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentCreateNoteBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.foundations.NullFieldChecker
import com.example.physiotherapy.model.Note
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.students.selectedStudentDetail.SSDetailFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateNoteFragment : BaseFragment(), NullFieldChecker {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCreateNoteBinding
    lateinit var model: INoteModel
    private lateinit var navController: NavController
    private lateinit var createViewModel: CreateViewModel
    private lateinit var selectedStudent: Student


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_note, container, false)
        createViewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        selectedStudent = arguments?.getSerializable("studentSSDetail") as Student;
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.saveButton.setOnClickListener {
            createNote()?.let { it1 -> createViewModel.addNewNote(it1, selectedStudent.id) }
            navigateToSSFragment()
        }
    }

    private fun navigateToSSFragment() {
        val createdNote =
            bundleOf("student" to selectedStudent)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_createNoteFragment_to_selectedStudentFragment,
            createdNote,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    private fun createNote(): Note? = if (!hasNullField()) {
        Note(noteEditText.editableText.toString())
    } else {
        null
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()


    companion object {
        fun newInstance(): CreateNoteFragment = newInstance()
    }
}