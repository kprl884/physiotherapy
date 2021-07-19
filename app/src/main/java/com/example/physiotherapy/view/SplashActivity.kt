package com.example.physiotherapy.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.physiotherapy.R
import com.example.physiotherapy.repository.FirebaseViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseViewModel: FirebaseViewModel
    private var currentFirebaseUser: FirebaseUser? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseViewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        coroutineScope.launch {
            currentFirebaseUser = firebaseViewModel.checkUserLoggedIn()
            //delay(3_000)
            if (currentFirebaseUser == null) {
                startActivityWithIsLogin(MainActivity(), false)
            } else {
                startActivityWithIsLogin(MainActivity(), true)
            }
        }
    }

    private fun startActivityWithIsLogin(activity: Activity, isNull: Boolean) {
        val intent = Intent(this@SplashActivity, activity::class.java)
        intent.putExtra("isLogin", isNull)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}