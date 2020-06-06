package com.example.android.firestore

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class AddIncomeActivity : AppCompatActivity() {
    var incomeCount: Int = 0
    private lateinit var incomeDate: EditText
    private lateinit var incomeDescription: EditText
    private lateinit var incomeAmount: EditText
    private lateinit var incomeSaveBtn: Button
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)

        incomeDate = findViewById(R.id.income_date_input)
        incomeDescription = findViewById(R.id.income_description_input)
        incomeAmount = findViewById(R.id.income_amount)
        incomeSaveBtn = findViewById(R.id.income_save_btn)

        incomeSaveBtn.setOnClickListener {
            val iDate = incomeDate.text.toString()
            val iDescription = incomeDescription.text.toString()
            val iAmount = incomeAmount.text.toString()

            if (TextUtils.isEmpty(iDate) || TextUtils.isEmpty(iDescription) || TextUtils.isEmpty(
                    iAmount
                )
            ) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val loginUser = FirebaseAuth.getInstance().currentUser
                if (loginUser != null) {
                    val email = loginUser.email

                    val emailVerified = loginUser.isEmailVerified

                    val uid = loginUser.uid

                    val user: MutableMap<String, Any> = HashMap()
                    user["date"] = iDate
                    user["amount"] = iAmount
                    user["description"] = iDescription
                    if (email != null) {

                        db.collection("users").document(email)
                            .collection("Income").add(user)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                        db.collection("users").document(email)
                            .collection("AllTransactions").add(user)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                        incomeCount++
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }
}
