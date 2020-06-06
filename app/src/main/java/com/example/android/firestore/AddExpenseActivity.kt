package com.example.android.firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddExpenseActivity : AppCompatActivity() {
    var expenseCount: Int = 0
    private lateinit var expenseDate: EditText
    private lateinit var expenseDescription: EditText
    private lateinit var expenseAmount: EditText
    private lateinit var expenseSaveBtn: Button
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        expenseDate = findViewById(R.id.expense_date_input)
        expenseDescription = findViewById(R.id.expense_description_input)
        expenseAmount = findViewById(R.id.expense_amount)
        expenseSaveBtn = findViewById(R.id.expense_save_btn)

        expenseSaveBtn.setOnClickListener {
            val iDate = expenseDate.text.toString()
            val iDescription = expenseDescription.text.toString()
            val iAmount = expenseAmount.text.toString()

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
                            .collection("Expense").add(user)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                        db.collection("users").document(email)
                            .collection("AllTransactions").add(user)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                        expenseCount++
                        val intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }
    }
}