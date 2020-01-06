package com.karolapp.ideaappkt.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.karolapp.ideaappkt.R


class EntranceActivity : AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entrance_main)
        mAuth = FirebaseAuth.getInstance()
//        getSupportActionBar().hide();
        if (mAuth.getCurrentUser() != null) {
            // User is signed in (getCurrentUser() will be null if not signed in)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}