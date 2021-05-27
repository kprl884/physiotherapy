package com.example.physiotherapy.view.auth.register

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentRegisterBinding
import com.example.physiotherapy.foundations.BaseFragment
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.example.physiotherapy.view.students.selectedStudentDetail.tasks.SSTasksFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val fragmentName: String = LoginFragment::class.java.simpleName
    private lateinit var auth: FirebaseAuth
    private lateinit var userName: String
    private lateinit var userSurname: String
    private var userPhoneNumber: Int? = null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var loginFragment : Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        loginFragment = LoginFragment.newInstance()

        binding.registerBtnRegister.setOnClickListener {
            registerUser()
        }
        binding.registerBtnBack.setOnClickListener {

            openFragment(newInstance(), loginFragment)
        }

        return binding.root
    }

    private fun registerUser() {
        email = binding.registerEtEmail.editableText.toString()
        password = binding.registerEtPassword.editableText.toString()
        if (email == null){
            Log.e("alp", email + "email bos")
        }
        if (password == null){
            Log.e("alp", password + "pass bos")
        }
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                context,
                getString(R.string.email_password_cant_empty),
                Toast.LENGTH_LONG
            ).show()

        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isComplete) {
                    /*
                    NavHostFragment.findNavController(this).navigate(
                        R.id.action_registerFragment_to_loginFragment2,
                        null,
                        navOptions { // Use the Kotlin DSL for building NavOptions
                            anim {
                                enter = android.R.animator.fade_in
                                exit = android.R.animator.fade_out
                            }
                        }
                    )
                     */

                    openFragment(newInstance(), loginFragment)
                }
            }.addOnFailureListener {
                it.let {
                    Toast.makeText(
                        context,
                        "Kayıt olurken hata oluştu. Lütfen tekrar deneyiniz",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("alp", it.localizedMessage)
                }
            }
        }

    }
    private fun openFragment(deletedFragment:Fragment, fragment: Fragment) {
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
}