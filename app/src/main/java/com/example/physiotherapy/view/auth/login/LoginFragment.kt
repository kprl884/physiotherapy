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
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.FragmentLoginBinding
import com.example.physiotherapy.view.MainFragment
import com.example.physiotherapy.view.auth.AuthListener
import com.example.physiotherapy.view.auth.register.RegisterFragment
import com.example.physiotherapy.view.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment(), AuthListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var enteredPassword: String
    private lateinit var enteredMail: String
    private val fragmentName: String = LoginFragment::class.java.simpleName
    private lateinit var auth: FirebaseAuth
    private var isLogin: Boolean? = null
    private val mainFragment = MainFragment.newInstance()
    private val homeFragment = HomeFragment.newInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        isLogin = arguments?.getParcelable("isLogin")

        //val userRecord: UserRecord = FirebaseAuth.getInstance().getUser(uid)

        binding.loginBtnLogin.setOnClickListener {
            enteredPassword = binding.loginEtPassword.editableText.toString()
            enteredMail = binding.loginEtUserMail.editableText.toString()

            if (enteredPassword.isEmpty() || enteredMail.isEmpty()) {
                Toast.makeText(
                    context,
                    "Şifre veya mail boş bırakılamaz. Lütfen tekrar deneyiniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                signWithMailPassword(enteredMail, enteredPassword)
            }
        }

        binding.loginBtnRegister.setOnClickListener {
            val registerFragment = RegisterFragment.newInstance()
            openFragment(registerFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //setToolbarVisibility(getString(R.string.app_name), View.GONE, fragmentName)
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


    private fun signWithMailPassword(mail: String, password: String) {
        auth.signInWithEmailAndPassword(mail, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //Signed
                openHomeFragment(newInstance(), homeFragment)
            }

        }.addOnFailureListener {
            it.let {
                Toast.makeText(
                    context,
                    "Giriş yaparken hata oluştu, lütfen tekrar deneyiniz.",
                    Toast.LENGTH_LONG
                ).show()
                Log.e("alp", it.localizedMessage)
            }
        }
    }

    fun checkUserNamePassword() {
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

    private fun openFragment(addedFragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, addedFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun openHomeFragment(deletedFragment: Fragment, addedFragment: Fragment) {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.detach(deletedFragment)
        transaction.replace(R.id.main_container, addedFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        fun newInstance() = LoginFragment()
    }

    fun sendPasswordResetEmail() {
        val emailAddress = "user@example.com"

        Firebase.auth.sendPasswordResetEmail(emailAddress)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Email sent.")
                }
            }
    }

    fun deleteUser() {
        val user = Firebase.auth.currentUser!!

        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                }
            }
    }

}