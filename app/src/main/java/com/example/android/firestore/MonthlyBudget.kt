package com.example.android.firestore

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MonthlyBudget : AppCompatActivity() {

    private lateinit var budgetAmount: EditText
    private lateinit var budgetSubmitBtn: Button
    val db = FirebaseFirestore.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_budget)

        val current_user = FirebaseAuth.getInstance().currentUser

        if (current_user != null) {
            val email = current_user.email

            if (email != null) {
                db.collection("users").document(email)
                    .get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            val userFirstName = document.data?.get("first")
                            findViewById<TextView>(R.id.user_welcome_msg).text = "Welcome $userFirstName"
                        } else {
                            println("No such document")
                        }
                    }
            }

            budgetAmount = findViewById(R.id.budget_amount)
            budgetSubmitBtn = findViewById(R.id.submit_budget_btn)

            budgetSubmitBtn.setOnClickListener {
                if (TextUtils.isEmpty(budgetAmount.text.toString())) {
                    Toast.makeText(this, "Please enter budget amount", Toast.LENGTH_SHORT).show()
                } else {
                    val budget: MutableMap<String, Any> = HashMap()
                    budget["BudgetAmount"] = budgetAmount.text.toString()
                    if (email != null) {
                        db.collection("users").document(email)
                            .collection("budget").document("myBudget").set(budget)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                    }

                    Toast.makeText(this, "Budget Details Saved", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }



    }
}
