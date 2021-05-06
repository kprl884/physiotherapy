package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.Todo
import com.example.physiotherapy.view.students.selectedStudentDetail.TouchActionDelegate


class SSTasksFragment : Fragment(), TouchActionDelegate {
    private lateinit var binding: FragmentSelectedStudentTasksBinding
    private var param1: String? = null
    private var param2: String? = null

    lateinit var touchActionDelegate: TouchActionDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {

            if (it is TouchActionDelegate) {
                touchActionDelegate = it
            }

        }
    }

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

        binding.selectedStudentTaskRecyclerView.layoutManager = LinearLayoutManager(context)
        var taskList = mutableListOf<Task>(Task("task 1", mutableListOf(
            Todo("test todo 1", true), Todo("test todo 2")
        )),
            Task("task 2"))
        val ssTaskAdapter = SSTaskAdapter(taskList, touchActionDelegate)
        binding.selectedStudentTaskRecyclerView.adapter = ssTaskAdapter
    }

    companion object {
        fun newInstance() = SSTasksFragment()
    }

    override fun onAddButtonClicked() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_SSTasksFragment_to_createFragment,
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