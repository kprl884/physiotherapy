package com.example.physiotherapy.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.physiotherapy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize




class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var refSplashRefUser: DatabaseReference? = null
    private var refSplashRefUserType: DatabaseReference? = null
    private var refSplashRefCodeBeginTime: DatabaseReference? = null
    private val database = FirebaseDatabase.getInstance()
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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