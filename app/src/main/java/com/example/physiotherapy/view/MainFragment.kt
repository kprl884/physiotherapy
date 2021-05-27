package com.example.physiotherapy.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentMainBinding
import com.example.physiotherapy.foundations.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : BaseFragment() {

    lateinit var binding: FragmentMainBinding
    private var param1: String? = null
    private var param2: String? = null

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
    ): View {
        Log.d("location","MainFragment")
        /*
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        requireActivity().setActionBar(binding.toolbarLayout.toolbarLayout)
        binding.toolbarLayout.toolbarBackBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        //val navController = findNavController()
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment)
        val navController = navHostFragment?.findNavController()




        //val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

        //val navController = navHostFragment.navController

         AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_students,
                R.id.navigation_profile
            )
        )

        if (navController != null) {
            navigation_bottom.setupWithNavController(navController)
        }
         */

        //navigation_bottom.setupWithNavController(navController)




        return binding.root
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}