package com.example.physiotherapy.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentHomeBinding
import com.example.physiotherapy.foundations.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    var fragmentName: String = HomeFragment::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        Log.d("location","HomeFragment")
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
        //setToolbarVisibility(getString(R.string.app_name), View.GONE, fragmentName)
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

}