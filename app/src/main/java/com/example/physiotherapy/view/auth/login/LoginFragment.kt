package com.example.physiotherapy.view.auth.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentLoginBinding
import com.example.physiotherapy.view.auth.AuthListener
import com.google.firebase.auth.FirebaseAuth
import com.example.physiotherapy.view.util.toast
import com.example.physiotherapy.viewmodel.AuthViewModel


class LoginFragment : Fragment(), AuthListener {
    private lateinit var binding:FragmentLoginBinding
    private var userPassword:String = ""
    private var userName:String = ""
    private var mAuth: FirebaseAuth? = null
    private lateinit var  viewModel:AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this
        return binding.root
    }


    override fun onStarted() {
        Toast.makeText(requireContext(), "Login Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSucces() {
        Toast.makeText(requireContext(), "Login Succes", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


}