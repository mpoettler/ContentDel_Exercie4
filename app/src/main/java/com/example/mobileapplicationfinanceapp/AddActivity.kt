package com.example.mobileapplicationfinanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btn_add_homescreen.setOnClickListener(){
            startActivity(Intent(this@AddActivity, FinanceMainPage::class.java))
        }

        btn_add_logout.setOnClickListener(){
            startActivity(Intent(this@AddActivity, LoginActivity::class.java))
        }

        btn_add_add.setOnClickListener(){


            val label:String = tiet_add_nameofexpense.text.toString()
            val moneyAmount:Double = tiet_add_value.text.toString().toDouble()

            val addTransactionToList:Transaction= Transaction(label,moneyAmount)

            val intent = Intent(this, FinanceMainPage::class.java).also {
                it.putExtra("EXTRA_NAME", addTransactionToList.label)
                it.putExtra("EXTRA_VALUE", addTransactionToList.amount.toString())
                startActivity(it)
            }

            if (moneyAmount == null)
                til_add_value.error = "Feld darf nicht leer sein ohne falsche Eingabe"
            if(label == null)
                til_add_name.error = "Feld darf nicht leer sein ohne falsche Eingabe"
        }
    }
}