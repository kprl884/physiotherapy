package com.example.physiotherapy.view.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentLoginBinding
import com.example.physiotherapy.repository.FirebaseViewModel
import com.example.physiotherapy.view.auth.register.RegisterFragment
import com.google.firebase.auth.FirebaseAuth


private val TAG = "LoginFragment"

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseViewModel: FirebaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        auth = FirebaseAuth.getInstance()
        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        //isLogin = arguments?.getParcelable("isLogin")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtnRegister.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance()
            openFragment(registerFragment)
        }

        binding.loginBtnLogin.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                Log.d("alp","validate email and password")
                firebaseViewModel.logInUserFromAuthWithEmailAndPassword(
                    binding.loginEtUserMail.text.toString(),
                    binding.loginEtPassword.text.toString(),
                    requireActivity()
                )
            }else {
                Log.d("LoginFragment","email or password wrong")
            }

        }
    }

    override fun onResume() {
        super.onResume()
        //setToolbarVisibility(getString(R.string.app_name), View.GONE, fragmentName)
    }

    private fun openFragment(addedFragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, addedFragment)
        transaction.commit()
    }

    private fun openHomeFragment(deletedFragment: Fragment, addedFragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.detach(deletedFragment)
        transaction.replace(R.id.main_container, addedFragment)
        transaction.commit()
    }

    private fun validateEmail(): Boolean {
        val email = binding.loginEtUserMail.text.toString().trim()

        return if (!email.contains("@") && !email.contains(".")) {
            binding.loginEtUserMail.error = "Enter a valid email"
            false
        } else if (email.length < 6) {
            binding.loginEtUserMail.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.loginEtPassword.text.toString().trim()

        return if (password.length < 6) {
            binding.loginEtPassword.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }


}