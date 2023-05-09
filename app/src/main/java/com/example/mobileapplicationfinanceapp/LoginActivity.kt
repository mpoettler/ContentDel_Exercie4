package com.example.mobileapplicationfinanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()



        login()
    }

    private fun login() {
        val loginButton = findViewById<Button>(R.id.btn_login_login)
        val emailInput = findViewById<EditText>(R.id.et_login_email)
        val passwordInput = findViewById<EditText>(R.id.et_login_password)
        val registerText = findViewById<TextView>(R.id.tv_login_register)
        val forgotPassword = findViewById<TextView>(R.id.tv_login_forgotpassword)

        loginButton.setOnClickListener() {
            if (TextUtils.isEmpty(emailInput.text.toString())) {
                emailInput.error = "Please enter Email"
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passwordInput.text.toString())) {
                passwordInput.error = "Please enter password"
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(
                emailInput.text.toString(),
                passwordInput.text.toString()
            ).addOnCompleteListener() {
                if (it.isSuccessful) {
                    startActivity(Intent(this@LoginActivity, FinanceMainPage::class.java))
                    finish()
                }
                else
                    Toast.makeText(
                        this@LoginActivity,
                        "Login Failed (Wrong Email or Password)",
                        Toast.LENGTH_SHORT
                    ).show()

            }
        }

        registerText.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
        forgotPassword.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
        }
    }
}