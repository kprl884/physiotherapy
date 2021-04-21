package com.example.physiotherapy.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentHomeBinding
import com.example.physiotherapy.view.BaseFragment

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        /*


        binding.homeBtnStudens.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_studentsFragment,null, navOptions { // Use the Kotlin DSL for building NavOptions
                anim {
                    enter = android.R.animator.fade_in
                    exit = android.R.animator.fade_out
                }
            })
        }
 */

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

}