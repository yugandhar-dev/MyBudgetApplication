package com.example.android.firestore

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    private lateinit var goalName: EditText
    private lateinit var goalAmount: EditText
    private lateinit var targetDate: EditText
    private lateinit var nextButton: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra(CURRENT_USER)

        val userTextview = findViewById<TextView>(R.id.welcome_text).apply {
            text = "Welcome $username"
        }

        goalName = findViewById(R.id.goal_name_input)
        goalAmount = findViewById(R.id.goal_amount_input)
        targetDate = findViewById(R.id.goal_target_date_input)
        nextButton = findViewById(R.id.next_btn)
       // val currentDate = LocalDateTime.now()

        nextButton.setOnClickListener {
            if (TextUtils.isEmpty(goalName.text.toString()) || TextUtils.isEmpty(goalAmount.text.toString()) ||
                    TextUtils.isEmpty(targetDate.text.toString())) {
                Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show()
//            } else if (currentDate < LocalDateTime.parse(targetDate.text.toString())) {
//                    Toast.makeText(this, "please enter future target date", Toast.LENGTH_SHORT).show()
//            }
                }else {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                val user = FirebaseAuth.getInstance().currentUser

                if (user != null) {

                    val email = user.email

                    val newDetails: MutableMap<String, Any> = HashMap()
                    newDetails["goalName"] = goalName.text.toString()
                    newDetails["goalAmount"] = goalAmount.text.toString()
                    newDetails["targetDate"] = targetDate.text.toString()
                    if (email != null) {
                        db.collection("users").document(email)
                            .collection("Goals").document("userGoals").set(newDetails)
                            .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                            .addOnFailureListener { e -> println("Error adding document $e") }
                    }
                    Toast.makeText(this, "Details Saved", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MonthlyBudget::class.java)
                    startActivity(intent)
                    finish()
                }


            }
        }
    }
}
