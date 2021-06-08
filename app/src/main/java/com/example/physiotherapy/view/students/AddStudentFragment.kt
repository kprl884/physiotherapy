package com.example.physiotherapy.view.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentAddStudentBinding
import com.example.physiotherapy.foundations.BaseFragment


class AddStudentFragment : BaseFragment() {
    private lateinit var binding: FragmentAddStudentBinding
    private lateinit var sName: String
    private lateinit var sSurname: String
    private var sPhoneNumber: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_student, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun createStudent() {
        sName = binding.addStudentEtName.editableText.toString()
        sSurname = binding.addStudentEtLastName.editableText.toString()
        sPhoneNumber = binding.addStudentEtPhoneNumber.editableText.toString().toInt()

    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE)
    }

    companion object {
        fun newInstance() = AddStudentFragment()
    }
}