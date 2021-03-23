package com.example.physiotherapy.view.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var userPassword:String = ""
    private var userName:String = ""
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        signInWithNameAndPassword(userName, userPassword)
        return binding.root
    }

    private fun signInWithNameAndPassword(userName: String, userPassword: String) {
        if (userName!= null){
            Log.d("alp", "signInWithName:success");

        }
        else {
            Log.w("alp", "signInWithName:failure");

        }
    }


}