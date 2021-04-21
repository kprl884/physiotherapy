package com.example.physiotherapy.view.auth.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentLoginBinding
import com.example.physiotherapy.view.BaseFragment
import com.example.physiotherapy.view.auth.AuthListener
import com.example.physiotherapy.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment(), AuthListener {
    private lateinit var binding:FragmentLoginBinding
    private var userPassword:String = ""
    private var userName:String = ""
    private var mAuth: FirebaseAuth? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        //val userRecord: UserRecord = FirebaseAuth.getInstance().getUser(uid)

        binding.loginBtnLogin.setOnClickListener {
            Log.d("alp","login basildi")
            findNavController(this).navigate(
                R.id.action_loginFragment_to_homeFragment,
                null,
                navOptions { // Use the Kotlin DSL for building NavOptions
                    anim {
                        enter = android.R.animator.fade_in
                        exit = android.R.animator.fade_out
                    }
                }
            )
        }
        return binding.root
    }

    override fun onResume() {
        setToolbarVisibility(resources.getString(R.string.app_name), View.GONE)
        super.onResume()
    }

    override fun onStarted() {
        Toast.makeText(requireContext(), "Login Started", Toast.LENGTH_SHORT).show()
    }

    override fun onSucces(userName: String, password: String) {
        //login process
        //home fragment
    }

    override fun onFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun checkUserNamePassword(){
        /*



        val docRef = db.collection("users").document("$currentUser")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed.", e)
                return@addSnapshotListener
            }

            val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                "Local"
            else
                "Server"

            if (snapshot != null && snapshot.exists()) {
                Log.d("TAG", "$source data: ${snapshot.data}")
            } else {
                Log.d("TAG", "$source data: null")
            }
        }*/
    }

    fun sendPasswordResetEmail(){
        val emailAddress = "user@example.com"

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
    }

    fun deleteUser(){
        val user = Firebase.auth.currentUser!!

        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                }
            }
    }

}