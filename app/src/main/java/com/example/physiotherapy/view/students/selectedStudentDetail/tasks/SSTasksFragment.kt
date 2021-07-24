package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.foundations.BaseFragment


class SSTasksFragment : BaseFragment() {
    private lateinit var binding: FragmentSelectedStudentTasksBinding
    lateinit var taskViewModel: SSTaskViewModel
    lateinit var contentView: SSTaskListView
    var TAG: String = SSTasksFragment::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_selected_student_tasks, container, false).apply {
            contentView = this as SSTaskListView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        setContentView()
    }

    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE, TAG)

    }

    private fun setContentView() {
        contentView.initView({ onAddButtonClicked() }, taskViewModel)
    }

    private fun bindViewModel() {
        taskViewModel = ViewModelProvider(this).get(SSTaskViewModel::class.java)

        taskViewModel.taskListLiveData.observe(viewLifecycleOwner, Observer { taskList ->
            //Update the adapter
            contentView.updateList(taskList)
        })
    }

    companion object {
        fun newInstance() = SSTasksFragment()
    }

    private fun onAddButtonClicked() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_selectedStudentFragment_to_createTaskFragment,
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