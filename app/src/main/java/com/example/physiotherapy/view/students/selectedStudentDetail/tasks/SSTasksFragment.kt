package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.Todo
import com.example.physiotherapy.view.students.selectedStudentDetail.TouchActionDelegate


class SSTasksFragment : Fragment() {
    private lateinit var binding: FragmentSelectedStudentTasksBinding
    lateinit var taskViewModel: SSTaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_selected_student_tasks,
            container,
            false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViewModel()

        binding.selectedStudentTaskRecyclerView.layoutManager = LinearLayoutManager(context)
        var taskList = taskViewModel.getFakeData()
        val ssTaskAdapter = SSTaskAdapter(taskList) { onAddButtonClicked() }
        binding.selectedStudentTaskRecyclerView.adapter = ssTaskAdapter
    }

    private fun bindViewModel(){
        taskViewModel = ViewModelProvider(this).get(SSTaskViewModel::class.java)
    }

    companion object {
        fun newInstance() = SSTasksFragment()
    }

    private fun onAddButtonClicked() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_selectedStudentFragment_to_createFragment,
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