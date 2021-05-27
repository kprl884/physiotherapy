package com.example.physiotherapy.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.physiotherapy.R
import com.example.physiotherapy.databinding.ActivityMainBinding
import com.example.physiotherapy.view.auth.login.LoginFragment
import com.example.physiotherapy.view.auth.register.RegisterFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var refSplashRefUser: DatabaseReference? = null
    private var refSplashRefUserType: DatabaseReference? = null
    private var refSplashRefCodeBeginTime: DatabaseReference? = null
    private val database = FirebaseDatabase.getInstance()
    private val db = Firebase.firestore
    lateinit var binding: ActivityMainBinding
    private var isLogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //Objects.requireNonNull(actionBar)!!.setDisplayHomeAsUpEnabled(true)
        setActionBar(binding.toolbarLayout.toolbarLayout)
        binding.toolbarLayout.toolbarBackBtn.setOnClickListener { onBackPressed() }

        mAuth = FirebaseAuth.getInstance()

        Firebase.initialize(this)
        val database = FirebaseDatabase.getInstance()
        refSplashRefUser = database.getReference("users")


        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.is_user_loggin), isLogin)
            apply()
        }

        val defaultValue = resources.getString((R.string.is_user_loggin_default_false))
        val status = defaultValue.toBoolean()
        //val loggingStatus = sharedPref.getBoolean(getString(R.string.is_user_loggin), status)

        val navController = findNavController(R.id.navigation_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.?

        AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_students,
                R.id.navigation_profile
            )
        )

        navigation_bottom.setupWithNavController(navController)
        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            val loginFragment = LoginFragment.newInstance()
            openFragment(loginFragment)
        }


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


        val a = db.collection("users")
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
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun registerUser(userId: String) {
        val refSplashRefUser: DatabaseReference?
        val database = FirebaseDatabase.getInstance()
        refSplashRefUser = database.getReference("users")
        refSplashRefUser.child(userId).child("user_name").setValue("alparslan")

        refSplashRefUser.child(userId).child("user_surname").setValue("köprülü")

        refSplashRefUser.child(userId).child("user_nick").setValue("alprsnk")

        refSplashRefUser.child(userId).child("user_password").setValue("123456")
    }
}