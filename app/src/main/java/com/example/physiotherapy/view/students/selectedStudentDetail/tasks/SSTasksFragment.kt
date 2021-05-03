package com.example.physiotherapy.view.students.selectedStudentDetail.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentTasksBinding
import com.example.physiotherapy.databinding.FragmentStudentsBinding
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.Todo


class SSTasksFragment : Fragment() {
    private lateinit var binding: FragmentSelectedStudentTasksBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selected_student_tasks, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.selectedStudentTaskRecyclerView.layoutManager = LinearLayoutManager(context)
        var taskList = mutableListOf<Task>(Task("task 1", mutableListOf(
         Todo("test todo 1", true), Todo("test todo 2")
        )),
        Task("task 2"))
        val ssTaskAdapter = SSTaskAdapter(taskList)
        binding.selectedStudentTaskRecyclerView.adapter = ssTaskAdapter
    }

    companion object{
        fun newInstance() = SSTasksFragment()
    }
}