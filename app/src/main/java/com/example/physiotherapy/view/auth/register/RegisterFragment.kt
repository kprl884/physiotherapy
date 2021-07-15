package com.example.physiotherapy.view.auth.register

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentRegisterBinding
import com.example.physiotherapy.repository.FirebaseViewModel
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val TAG = "RegisterFragment"

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val fragmentName: String = LoginFragment::class.java.simpleName
    private lateinit var auth: FirebaseAuth
    private lateinit var loginFragment: Fragment
    private lateinit var firebaseViewModel: FirebaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        loginFragment = LoginFragment.newInstance()
        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtnRegister.setOnClickListener {
            if (validateName() && validateEmail() && validatePassword()) {
                firebaseViewModel.registerUserFromAuthWithEmailAndPassword(
                    binding.registerEtName.text.toString(),
                    binding.registerEtPhoneNumber.text.toString(),
                    binding.registerEtEmail.text.toString(),
                    binding.registerEtPassword.text.toString(),
                    requireActivity()
                )
            }
        }
        binding.registerBtnBack.setOnClickListener {
            openFragment(newInstance(), loginFragment)
        }
    }


    private fun openFragment(deletedFragment: Fragment, fragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.detach(deletedFragment)
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun setPassword() {
        val newPassword = "SOME-SECURE-PASSWORD"
        val user = Firebase.auth.currentUser
        user!!.updatePassword(newPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User password updated.")
                }
            }
    }

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun onResume() {
        super.onResume()
        //setToolbarVisibility(getString(R.string.app_name), View.GONE, fragmentName)
    }


    private fun validateName(): Boolean {
        val name = binding.registerEtName.text.toString().trim()

        return if (name.length < 6) {
            binding.registerEtName.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.registerEtEmail.text.toString().trim()

        return if (!email.contains("@") && !email.contains(".")) {
            binding.registerEtEmail.error = "Enter a valid email"
            false
        } else if (email.length < 6) {
            binding.registerEtEmail.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.registerEtPassword.text.toString().trim()

        return if (password.length < 6) {
            binding.registerEtPassword.error = "Use at least 5 characters"
            false
        } else {
            true
        }
    }
}