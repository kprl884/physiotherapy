package com.example.physiotherapy.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.ActivityMainBinding
import com.example.physiotherapy.view.home.HomeFragment
import com.example.physiotherapy.view.profile.ProfileFragment
import com.example.physiotherapy.view.students.StudentsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var refSplashRefUser: DatabaseReference? = null
    private var refSplashRefUserType: DatabaseReference? = null
    private var refSplashRefCodeBeginTime: DatabaseReference? = null
    private val database = FirebaseDatabase.getInstance()
    private val db = Firebase.firestore
    lateinit var binding: ActivityMainBinding
    private var isLoggin = true // TODO : get if user loggin or not and decide begin fragment home or login
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {

                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_students -> {
                val studentsFragment = StudentsFragment.newInstance()
                openFragment(studentsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                val profileFragment = ProfileFragment.newInstance()
                openFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Objects.requireNonNull(actionBar)!!.setDisplayHomeAsUpEnabled(true)
        setActionBar(binding.toolbarLayout.toolbarLayout)
        binding.toolbarLayout.toolbarBackBtn.setOnClickListener { onBackPressed() }

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.is_user_loggin), isLoggin)
            apply()
        }

        val defaultValue = resources.getString((R.string.is_user_loggin_default_false))
        val status = defaultValue.toBoolean()
        val logginStatus = sharedPref.getBoolean(getString(R.string.is_user_loggin), status)
        if (logginStatus) {
            val homeFragment = HomeFragment.newInstance()
            openFragment(homeFragment)
        }
        navigation_bottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        /*

        TODO eğer giriş yapılmışsa home fragmentte başla
        giriş yapılmamışsa login fragmentle başla
         */


        mAuth = FirebaseAuth.getInstance();
        Firebase.initialize(this)
        val database = FirebaseDatabase.getInstance()
        refSplashRefUser = database.getReference("users")

        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

        // Add a new document with a generated ID
        /*
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }
         */


        val a =db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        //updateUI(currentUser)
    }

    private fun registerUser(userId: String) {
        val refSplashRefUser: DatabaseReference?
        val database = FirebaseDatabase.getInstance()
        refSplashRefUser = database.getReference("users")
        refSplashRefUser.child(userId).
        child("user_name").setValue("alparslan")

        refSplashRefUser.child(userId).
        child("user_surname").setValue("köprülü")

        refSplashRefUser.child(userId).
        child("user_nick").setValue("alprsnk")

        refSplashRefUser.child(userId).
        child("user_password").setValue("123456")
    }
}