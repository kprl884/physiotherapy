package com.example.physiotherapy.view.students.studentList.addStudent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentAddStudentBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.utils.Result


class AddStudentFragment : BaseFragment() {
    private lateinit var binding: FragmentAddStudentBinding
    private lateinit var sName: String
    private lateinit var sSurname: String
    private var sPhoneNumber: Int? = null
    private lateinit var addStudentViewModel: AddStudentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_student, container, false)
        addStudentViewModel = ViewModelProvider(this).get(AddStudentViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            navigateStudentsFragment()
        }

        binding.btnAddStudent.setOnClickListener {
            if (validateIdentify() && validateName() && validatePhoneNumber()) {
                val studentObject = newStudentObject()
                addStudentViewModel.addNewStudentToFireStore(studentObject)
                addStudentViewModel.currentResultLD.observe(viewLifecycleOwner, Observer {
                    if (it == Result.Success<Student>(studentObject)) {
                        navigateStudentsFragment()
                    } else {
                        //toast message
                        //todo: toast message
                    }
                })
            }
        }
    }

    private fun navigateStudentsFragment() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_addStudentFragment_to_navigation_students,
            null,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }

    private fun newStudentObject(): Student {
        return Student(
            binding.tietStudentName.text.toString().trim(),
            binding.tietStudentIdNo.text.toString().trim(),
            binding.tietStudentIdPhone.text.toString().trim()
        )
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE)
    }

    companion object {
        fun newInstance() = AddStudentFragment()
    }


    private fun validateName(): Boolean {
        val name = binding.tietStudentName.text.toString().trim()

        return if (name.length < 3) {
            binding.tietStudentName.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }

    private fun validatePhoneNumber(): Boolean {
        val phone = binding.tietStudentIdPhone.text.toString().trim()

        return if (phone.length < 11) {
            binding.tietStudentIdPhone.error = "Use at least 11 characters"
            false
        } else {
            true
        }
    }

    private fun validateIdentify(): Boolean {
        val idNo = binding.tietStudentIdNo.text.toString().trim()
        return if (idNo.length < 11) {
            binding.tietStudentIdNo.error = "Use at least 11 characters"
            false
        } else {
            true
        }
    }
}