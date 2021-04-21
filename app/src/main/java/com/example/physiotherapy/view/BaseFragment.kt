package com.example.physiotherapy.view

import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    protected fun setToolbarVisibility(title: String?,visibility:Int) {
        (this.requireActivity() as MainActivity).binding.toolbarLayout.labelText = title
        if(visibility== View.VISIBLE)
            (this.requireActivity() as MainActivity).actionBar?.show()
        else
            (this.requireActivity() as MainActivity).actionBar?.hide()
    }
}