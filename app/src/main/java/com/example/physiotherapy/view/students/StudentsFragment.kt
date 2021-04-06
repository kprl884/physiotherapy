package com.example.physiotherapy.view.students

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentStudentsBinding
import com.example.physiotherapy.model.Student


class StudentsFragment : Fragment() {
    private lateinit var binding: FragmentStudentsBinding
    val studentList = listOf(Student("Alparslan", "Köprülü", "alprslnk", "123456", 1),
        Student("Bera", "Gelebek", "beraglk", "123456", 2),
        Student("mustafa", "seki", "mstfsk", "123456", 3),
        Student("kedi", "ak", "kdrak", "123456", 4),
        Student("enes", "güreli", "enesgrl", "123456", 5))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_students, container, false)

        setRecyclerView()
        binding.studentFragBtnStudentAdd.setOnClickListener {
                it.findNavController().navigate(R.id.action_studentsFragment_to_addStudentFragment,null, navOptions { // Use the Kotlin DSL for building NavOptions
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                })
        }
        return binding.root
    }

    private fun setRecyclerView() {
        binding.apply {
            studentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            studentRecyclerView.adapter = StudentsFragmentAdapter(studentList, {selectedStudentItem:Student ->editStudentBtn(selectedStudentItem)},
                {selectedStudentItem:Student -> deleteStudentBtn(selectedStudentItem)},
                {selectedStudentItem:Student -> selectedStudentDetail(selectedStudentItem)}
                )
        }
    }

    private fun editStudentBtn(selectedStudentItem: Student) {
        Toast.makeText(context, "editStudent ", Toast.LENGTH_SHORT).show()

    }

    private fun deleteStudentBtn(selectedStudentItem: Student) {
        Toast.makeText(context, "deleteStudent", Toast.LENGTH_SHORT).show()

    }

    private fun selectedStudentDetail(selectedStudentItem: Student){
        NavHostFragment.findNavController(this).navigate(
            R.id.action_studentsFragment_to_selectedStudentFragment,
            null,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }
}