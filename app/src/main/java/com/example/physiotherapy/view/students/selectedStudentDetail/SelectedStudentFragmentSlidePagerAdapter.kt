package com.example.physiotherapy.view.students.selectedStudentDetail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.physiotherapy.model.Student
import com.example.physiotherapy.view.students.selectedStudentDetail.notes.SelectedStudentNotesFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SelectedStudentTasksFragment


class SelectedStudentFragmentSlidePagerAdapter( fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val mFragmentList = arrayListOf<Fragment>(SelectedStudentTasksFragment(),SelectedStudentNotesFragment())
    override fun getItemCount():Int = mFragmentList.size
    override fun createFragment(position: Int): Fragment = mFragmentList[position]
}