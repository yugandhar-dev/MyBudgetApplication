package com.example.android.firestore

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Dashboard : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val homeFragment = HomeFragment()
    private val incomeFragment = IncomeFragment()
    private val expenseFragment = ExpenseFragment()

    private lateinit var fab: FloatingActionButton
    private lateinit var fabIncome: FloatingActionButton
    private lateinit var fabExpense: FloatingActionButton
    private lateinit var fabGoals: FloatingActionButton
    private lateinit var fabTextIncome: TextView
    private lateinit var fabTextExpense: TextView
    private lateinit var fabTextGoals: TextView

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var fabClock: Animation
    private lateinit var fabAnticlock: Animation

    private lateinit var homeBtn: ImageButton
    private lateinit var incomeBtn: ImageButton
    private lateinit var expenseBtn: ImageButton
    private lateinit var savingsBtn: ImageButton

    private var isOpen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, homeFragment)
        fragmentTransaction.commit()

        homeBtn = findViewById(R.id.home_btn)
        incomeBtn = findViewById(R.id.income_btn)
        expenseBtn = findViewById(R.id.expense_btn)
        savingsBtn = findViewById(R.id.savings_btn)

        fab = findViewById(R.id.fab)
        fabIncome = findViewById(R.id.fab_income)
        fabExpense = findViewById(R.id.fab_expense)
        fabGoals = findViewById(R.id.fab_goals)

        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        fabClock = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_clock)
        fabAnticlock = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_anticlock)

        fabTextIncome = findViewById(R.id.income_textView)
        fabTextExpense = findViewById(R.id.expense_textView)
        fabTextGoals = findViewById(R.id.goals_textView)

        homeBtn.setOnClickListener {homeBtn()}
        incomeBtn.setOnClickListener {incomeBtn()}
        expenseBtn.setOnClickListener {expenseBtn()}
//        savingsBtn.setOnClickListener {savingsBtn()}
        fab.setOnClickListener { fabMenu() }

        fabIncome.setOnClickListener {
            val intent = Intent(this, AddIncomeActivity::class.java)
            startActivity(intent)
        }

        fabExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun homeBtn() {

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, homeFragment)
        fragmentTransaction.commit()
    }

    private fun incomeBtn() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, incomeFragment)
        fragmentTransaction.commit()
    }

    private fun expenseBtn() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, expenseFragment)
        fragmentTransaction.commit()
    }

    private fun fabMenu() {
        if (isOpen) {
            fabTextIncome.visibility = View.INVISIBLE
            fabTextExpense.visibility = View.INVISIBLE
            fabTextGoals.visibility = View.INVISIBLE
            fabIncome.startAnimation(fabClose)
            fabExpense.startAnimation(fabClose)
            fabGoals.startAnimation(fabClose)
            fab.startAnimation(fabAnticlock)
            fabIncome.isClickable = false
            fabExpense.isClickable = false
            fabGoals.isClickable = false
            isOpen = false
        } else {
            fabTextIncome.visibility = View.VISIBLE
            fabTextExpense.visibility = View.VISIBLE
            fabTextGoals.visibility = View.VISIBLE
            fabIncome.startAnimation(fabOpen)
            fabExpense.startAnimation(fabOpen)
            fabGoals.startAnimation(fabOpen)
            fab.startAnimation(fabClock)
            fabIncome.isClickable = true
            fabExpense.isClickable = true
            fabGoals.isClickable = true
            isOpen = true
        }
    }
}
