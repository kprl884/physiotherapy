package com.example.physiotherapy.view.students.studentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentStudentsBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.model.Student


class StudentsFragment : BaseFragment() {
    private lateinit var binding: FragmentStudentsBinding
    lateinit var studentViewModel: StudentsViewModel
    private val studentList = mutableListOf<Student>()
    private var adapter : StudentsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_students, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        bindViewModel()
    }


    private fun bindViewModel() {
        studentViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        studentViewModel.mutableStudentList.observe(viewLifecycleOwner, Observer {
            //Update the adapter
            adapter?.updateList(it)

        })
    }

    companion object {
        fun newInstance(): StudentsFragment = StudentsFragment()
    }

    private fun setRecyclerView() {


        binding.studentRecyclerView.layoutManager = LinearLayoutManager(context)


        adapter = StudentsAdapter(studentList,
            { addStudentDetail() },
            { selectedStudentItem: Student -> selectedStudentDetail(selectedStudentItem) }
        )
        binding.studentRecyclerView.adapter = adapter

    }

    private fun addStudentDetail() {


        NavHostFragment.findNavController(this).navigate(
            R.id.action_studentsFragment_to_addStudentFragment,
            null,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    private fun selectedStudentDetail(selectedStudentItem: Student? = null) {
        val bundle =
            bundleOf("student" to selectedStudentItem)


        NavHostFragment.findNavController(this).navigate(
            R.id.action_studentsFragment_to_selectedStudentFragment,
            bundle,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    private fun onAddButtonClicked() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_selectedStudentFragment_to_createNoteFragment,
            null,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE)
    }
}