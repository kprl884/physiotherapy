package com.example.physiotherapy.foundations

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.physiotherapy.view.MainActivity
import com.example.physiotherapy.view.MainFragment
import com.example.physiotherapy.view.home.HomeFragment
import com.example.physiotherapy.view.students.create.CreateFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.SSDetailFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_layout.view.*

open class BaseFragment : Fragment() {
    private var mainActivity: MainActivity? = null
    //private val mainFragment = MainFragment.newInstance()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun setToolbarVisibility(
        title: String?,
        visibility: Int?,
        fragmentTag: String? = null
    ) {
        mainActivity!!.binding.toolbarLayout.apply {
            toolbarTitle.text = title
            if (visibility != null) {
                toolbarBackBtn.visibility = visibility
            }
            toolbarSaveBtn.visibility = View.VISIBLE
        }
    }
}