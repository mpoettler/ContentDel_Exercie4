package com.example.mobileapplicationfinanceapp
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference?.child("profile")
        setContentView(R.layout.activity_register)


        register()
    }

    private fun register() {
        val registerButton = findViewById<Button>(R.id.btn_register_register)
        val firstName = findViewById<EditText>(R.id.et_register_firstname)
        val lastName = findViewById<EditText>(R.id.et_register_lastname)
        val email = findViewById<EditText>(R.id.et_register_email)
        val password = findViewById<EditText>(R.id.et_register_password)
        val confrimPassword = findViewById<EditText>(R.id.et_register_confirmpassword)



        registerButton.setOnClickListener {

            if(TextUtils.isEmpty(firstName.text.toString())) {
                firstName.error = "Please enter first name "
                return@setOnClickListener
            } else if(TextUtils.isEmpty(lastName.text.toString())) {
                lastName.error = "Please enter last name "
                return@setOnClickListener
            }else if(TextUtils.isEmpty(email.text.toString())) {
                email.error = "Please enter user name "
                return@setOnClickListener
            }else if(TextUtils.isEmpty(password.text.toString())) {
                password.error = "Please enter password "
                return@setOnClickListener
            }else if(TextUtils.isEmpty(confrimPassword.text.toString())) {
                confrimPassword.error = "Please enter Confrimpassword "
                return@setOnClickListener
            }


            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("firstname")?.setValue(firstName.text.toString())
                        currentUSerDb?.child("lastname")?.setValue(lastName.text.toString())

                        Toast.makeText(this@RegisterActivity, "Registration Success. ", Toast.LENGTH_LONG).show()
                        finish()

                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }


}






