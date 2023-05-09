package com.example.mobileapplicationfinanceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_finance_main_page.*

class FinanceMainPage : AppCompatActivity() {

    private lateinit var transaction: ArrayList<Transaction>
    private lateinit var transactionAdapater: TransactionAdapater
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var recyVie: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_finance_main_page)

        transaction = arrayListOf(
            Transaction("Gehalt", 3500.00),
            Transaction("Einkaufen", -400.00),
            Transaction("Auto", -250.00),
            Transaction("Kino", -10.00),
            Transaction("Urlaubt", -700.00),
            Transaction("Amazon", -83.00),
        )

        btn_finance_main_addbutton.setOnClickListener() {
            startActivity(Intent(this@FinanceMainPage, AddActivity::class.java))
        }



        transactionAdapater = TransactionAdapater(transaction)
        linearLayoutManager = LinearLayoutManager(this)

        rv_finance_main.apply {
            adapter = transactionAdapater
            layoutManager = linearLayoutManager
        }

        updateList()

        btn_finance_main_logout.setOnClickListener() {
            startActivity(Intent(this@FinanceMainPage, LoginActivity::class.java))
        }

        val swipeToDeleteCallback = object  : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                transaction.removeAt(position)
                rv_finance_main.adapter?.notifyItemRemoved(position)
                stageUpdate()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(rv_finance_main)

}
        private fun updateList(){
        val message = intent.getStringExtra("EXTRA_NAME")
        val addedValue = intent.getStringExtra("EXTRA_VALUE")?.toDouble()

        if(message != null && addedValue != null){
            transaction.add(Transaction(message,addedValue))
        }

        val CompleteMoneyAmount :Double = transaction.map { it.amount }.sum()
        val budgetAmount: Double = transaction.filter { it.amount > 0 }.map { it.amount }.sum()
        val expense = CompleteMoneyAmount - budgetAmount

        tv_finance_main_kontostandineuro.text = "€ %.2f".format(CompleteMoneyAmount)
        tv_finance_main_budget.text = "€ %.2f".format(budgetAmount)
        tv_finance_main_expenses.text = "€ %.2f".format(expense)

    }
        private fun stageUpdate(){
            val CompleteMoneyAmount :Double = transaction.map { it.amount }.sum()
            val budgetAmount: Double = transaction.filter { it.amount > 0 }.map { it.amount }.sum()
            val expense = CompleteMoneyAmount - budgetAmount

            tv_finance_main_kontostandineuro.text = "€ %.2f".format(CompleteMoneyAmount)
            tv_finance_main_budget.text = "€ %.2f".format(budgetAmount)
            tv_finance_main_expenses.text = "€ %.2f".format(expense)
        }


}


