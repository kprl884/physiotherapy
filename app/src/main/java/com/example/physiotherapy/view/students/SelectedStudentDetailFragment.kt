package com.example.physiotherapy.view.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentSelectedStudentBinding
import com.example.physiotherapy.model.Tag
import com.example.physiotherapy.model.Task
import com.google.android.material.bottomnavigation.BottomNavigationView


class SelectedStudentDetailFragment : Fragment() {

    private lateinit var binding: FragmentSelectedStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_selected_student,
            container,
            false)
        val tag:Tag = Tag("High Priority", R.color.colorPrimary)

        val task = Task("Get Groceries")

        val s = task.title
       // binding.selectedStudentNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        binding.selectedStudentNavView.itemIconTintList = null;


        return binding.root
    }
}