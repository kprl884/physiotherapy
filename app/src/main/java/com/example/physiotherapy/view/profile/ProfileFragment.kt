package com.example.physiotherapy.view.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentProfileBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : BaseFragment() {
    private lateinit var mAuth: FirebaseAuth
    private val fragmentName: String = ProfileFragment::class.java.simpleName
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("location","ProfileFragment")
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        mAuth = FirebaseAuth.getInstance()
        binding.profileBtnLogOut.setOnClickListener {
            mAuth.signOut()
            val loginFragment = LoginFragment.newInstance()
            openFragment(newInstance(), loginFragment)


        }

        return binding.root
    }

    private fun logOut(){

    }

    private fun openFragment(deletedFragment:Fragment, fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.remove(deletedFragment)
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onResume() {
        super.onResume()
        setToolbarVisibility(getString(R.string.app_name), View.GONE, fragmentName)
    }

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}