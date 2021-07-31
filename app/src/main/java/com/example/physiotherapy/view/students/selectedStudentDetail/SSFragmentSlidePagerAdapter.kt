package com.example.physiotherapy.view.students.selectedStudentDetail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.students.selectedStudentDetail.notes.SSNotesFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment


class SSFragmentSlidePagerAdapter(fragment: Fragment, selectedStudent: Student?) : FragmentStateAdapter(fragment) {

    private val mFragmentList = arrayListOf<Fragment>(SSTasksFragment(selectedStudent),SSNotesFragment(selectedStudent))
    override fun getItemCount():Int = mFragmentList.size
    override fun createFragment(position: Int): Fragment = mFragmentList[position]
}