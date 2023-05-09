package com.example.mobileapplicationfinanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}