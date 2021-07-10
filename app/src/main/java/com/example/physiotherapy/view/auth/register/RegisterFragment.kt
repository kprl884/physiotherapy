package com.example.physiotherapy.view.auth.register

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
import com.example.physiotherapy.databinding.FragmentRegisterBinding
import com.example.physiotherapy.model.Physiotherapist
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val fragmentName: String = LoginFragment::class.java.simpleName
    private lateinit var auth: FirebaseAuth
    private lateinit var userName: String
    private lateinit var userSurname: String
    private var userPhoneNumber: String? = null
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var loginFragment: Fragment
    private val db = Firebase.firestore
    private lateinit var database: DatabaseReference
    private lateinit var user:Physiotherapist
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        auth = FirebaseAuth.getInstance()
        loginFragment = LoginFragment.newInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtnRegister.setOnClickListener {
            email = binding.registerEtEmail.editableText.toString()
            password = binding.registerEtPassword.editableText.toString()
            userName = binding.registerEtName.editableText.toString()
            userSurname = binding.registerEtLastName.editableText.toString()
            userPhoneNumber = binding.registerEtPhoneNumber.editableText.toString()

            user = createPhysiotherapist(
                userName,
                userSurname,
                userPhoneNumber!!,
                email,
                password
            )
            registerUser(user)
        }
        binding.registerBtnBack.setOnClickListener {
            openFragment(newInstance(), loginFragment)
        }
    }

    private fun registerUser(user: Physiotherapist) {
        if (email.isEmpty() or password.isEmpty()) {
            Toast.makeText(
                context,
                getString(R.string.email_password_cant_empty),
                Toast.LENGTH_LONG
            ).show()

        } else {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isComplete) {
                    openFragment(newInstance(), loginFragment)
                    setFirebaseForUser(user)
                }
            }.addOnFailureListener {
                it.let {
                    Toast.makeText(
                        context,
                        getString(R.string.register_failed),
                        Toast.LENGTH_LONG
                    ).show()
                    Log.e("alp", it.localizedMessage)
                }
            }
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

    private fun setFirebaseForUser(user:Physiotherapist) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    private fun createPhysiotherapist(
        name: String,
        surName: String,
        phoneNumber: String,
        mail: String,
        password: String
    ): Physiotherapist {
        return Physiotherapist(name, surName, phoneNumber, mail, password)
    }
}