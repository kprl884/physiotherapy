package com.example.physiotherapy.view.auth.login

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import com.example.physiotherapy.view.auth.FirebaseViewModel
import com.example.physiotherapy.view.MainActivity
import com.example.physiotherapy.view.auth.register.RegisterFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.dialog_forgot_password.view.*


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
            val email = binding.loginEtUserMail.text.toString().trim()
            if (validateEmail(email) && validatePassword()) {
                Log.d("alp", "validate email and password")
                firebaseViewModel.logInUserFromAuthWithEmailAndPassword(
                    binding.loginEtUserMail.text.toString(),
                    binding.loginEtPassword.text.toString(),
                    requireActivity()
                )
            } else {
                Log.d("LoginFragment", "email or password wrong")
            }

        }

        binding.tvLoginForgotPassword.setOnClickListener {
            startForgotPasswordDialog()
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

    private fun validateEmail(email: String): Boolean {


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

    private fun startForgotPasswordDialog() {
        val dialogView = LayoutInflater.from(context).inflate(
            R.layout.dialog_forgot_password,
            null
        ) // 1
        val builder = AlertDialog.Builder(context).setView(dialogView) // 2
        val dialog: AlertDialog = builder.show() // 3

        dialogView.btn_forgotpassword_send.setOnClickListener { // 4
            val email = dialogView.tiet_forgotpassword_email.text.toString().trim()
            if (validateEmail(email)) // 5
            {
                firebaseViewModel.sendPasswordResetEmail(
                    email = email,
                    MainActivity()
                )
                dialog.dismiss() // 6
            }
        }

        dialogView.btn_forgotpassword_cancel.setOnClickListener { // 7
            dialog.dismiss() // 8
        }

        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 9
    }
}