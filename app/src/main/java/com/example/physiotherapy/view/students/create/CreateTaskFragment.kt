package com.example.physiotherapy.view.students.create

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentCreateTaskBinding
import com.example.physiotherapy.foundations.StateChangeTextWatcher
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.views.CreateTodoView
import kotlinx.android.synthetic.main.fragment_create_task.*
import kotlinx.android.synthetic.main.view_create_todo.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCreateTaskBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_task, container, false)
        //selectedStudent = arguments?.getSerializable("studentSSDetail") as Student;

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.createTaskView.taskEditText.addTextChangedListener(object :
            StateChangeTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                // add if p0 is not Empty and if p0 has changed from empty to no empty
                Log.d("exexex", "before if")
                if (!p0.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    Log.d(
                        "exexex",
                        "taskedittext previousValue.isNullOrEmpty = ${previousValue.toString()}"
                    )
                    addTodoView()
                }
                if (previousValue.isNullOrEmpty()) {
                    Log.d("exexex", "taskeditText previousValue = ${previousValue.toString()}")
                } else if (!previousValue.isNullOrEmpty()) {
                    Log.d("exexex", "taskeditText previousValue = ${previousValue.toString()}")
                }
                super.afterTextChanged(p0)
            }
        })
    }

    private fun addTodoView() {
        Log.d("exexex", "in addTodoView fun")
        val view = LayoutInflater.from(context)
            .inflate(R.layout.view_create_todo, containerView, false) as CreateTodoView

        view.todoEditText.addTextChangedListener(object : StateChangeTextWatcher() {
            override fun afterTextChanged(p0: Editable?) {
                if (previousValue.isNullOrEmpty()) {
                    Log.d("exexex", "addtodo previousValue = ${previousValue.toString()}")
                } else if (!previousValue.isNullOrEmpty()) {
                    Log.d("exexex", "addtodo previousValue = ${previousValue.toString()}")
                }
                if (!p0.isNullOrEmpty() && previousValue.isNullOrEmpty()) {
                    Log.d(
                        "exexex",
                        "addtodoview previousValue.isNullOrEmpty = ${previousValue.toString()}"
                    )
                    addTodoView()
                } else if (p0.isNullOrEmpty() && !previousValue.isNullOrEmpty()) {
                    removeTodoView(view)
                }
                super.afterTextChanged(p0)

            }
        })

        containerView.addView(view)
    }

    private fun removeTodoView(view: View) {
        containerView.removeView(view)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}