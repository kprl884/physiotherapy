package com.example.physiotherapy.view.students.create

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentCreateTaskBinding
import com.example.physiotherapy.foundations.StateChangeTextWatcher
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.model.Task
import com.example.physiotherapy.model.Todo
import com.example.physiotherapy.view.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.fragment_create_task.view.*
import kotlinx.android.synthetic.main.view_create_task.view.*
import kotlinx.android.synthetic.main.view_create_todo.view.*

private const val MAX_TODO_COUNT = 5

class CreateTaskFragment : Fragment() {
    private lateinit var binding: FragmentCreateTaskBinding
    private lateinit var selectedStudent: Student
    private lateinit var createViewModel: CreateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_task, container, false)
        createViewModel = ViewModelProvider(this).get(CreateViewModel::class.java)
        selectedStudent = arguments?.getSerializable("studentSSDetail") as Student;
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createTaskView.taskEditText.addTextChangedListener(object :
            StateChangeTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                // add if p0 is not Empty and if p0 has changed from empty to no empty
                if (!p0.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    addTodoView()
                }
                super.afterTextChanged(p0)
            }
        })

        binding.saveButton.setOnClickListener {
            saveTask()
            navigateToSSFragment()
        }
    }

    private fun addTodoView() {
        if (canAddTodos()) {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView

            view.todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
                override fun afterTextChanged(p0: Editable?) {
                    if (!p0.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                        addTodoView()
                    } else if (p0.isNullOrEmpty() && !previousValue.isNullOrEmpty()) {
                        removeTodoView(view)
                        // when todos removed and total equal max_todo_count then there are one more todos store
                        if (containerView.childCount == MAX_TODO_COUNT) {
                            addTodoView()
                        }
                    }
                    super.afterTextChanged(p0)
                }
            })
            containerView.addView(view)
        }
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }

    private fun canAddTodos(): Boolean = containerView.childCount < MAX_TODO_COUNT + 1

    private fun isTaskEmpty(): Boolean =
        binding.createTaskView.taskEditText.editableText.isNullOrEmpty()

    private fun saveTask() {
        if (!isTaskEmpty()) {
            containerView.run {
                var taskTitle: String? = null
                val todoList: MutableList<Todo> = mutableListOf()
                for (i in 0 until containerView.childCount) {
                    if (i == 0) {
                        // task view
                        taskTitle =
                            containerView.getChildAt(i).task_edit_text.editableText?.toString()
                    } else {
                        //_todo view
                        if (!containerView.getChildAt(i).todoEditText.editableText.isNullOrEmpty()) {
                            todoList.add(
                                Todo(
                                    containerView.getChildAt(i).todoEditText.editableText.toString()
                                )
                            )
                        }
                    }
                }
                if (taskTitle != null) {
                    createViewModel.addNewTask(Task(taskTitle, todoList), selectedStudent.id)
                }
            }
        }
    }

    private fun navigateToSSFragment() {
        val createdNote =
            bundleOf("student" to selectedStudent)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_createTaskFragment_to_selectedStudentFragment,
            createdNote,
            navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            }
        )
    }
    companion object {
        @JvmStatic
        fun newInstance() = CreateTaskFragment()
    }
}