package com.example.android.firestore

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

const val NEW_USER = "current user"

class SignupActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var user_email: EditText
    private lateinit var user_password: EditText
    private lateinit var confPassword: EditText
    private lateinit var signupBtn: Button
    private lateinit var backtoLogin: TextView
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mAuth = FirebaseAuth.getInstance()

        fName = findViewById(R.id.firstName)
        lName = findViewById(R.id.lastName)
        user_email = findViewById(R.id.emailAddress)
        user_password = findViewById(R.id.password)
        confPassword = findViewById(R.id.confirmPassword)
        signupBtn = findViewById(R.id.signup_button)

        signupBtn.setOnClickListener {
            val email = user_email.text.toString()
            val password = user_password.text.toString()
            val confirm_password = confPassword.text.toString()

            if (TextUtils.isEmpty(fName.text.toString()) || TextUtils.isEmpty(lName.text.toString()) || TextUtils.isEmpty(
                    email
                )
                || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password)
            ) {

                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirm_password) {
                Toast.makeText(this, "Passwords are not equal", Toast.LENGTH_SHORT).show()
            } else {


                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val user: MutableMap<String, Any> = HashMap()
                            user["first"] = fName.text.toString()
                            user["last"] = lName.text.toString()
                            user["email"] = email

                            db.collection("users").document(email)
                                .set(user)
                                .addOnSuccessListener { documentReference -> println("DocumentSnapshot added with ID: $documentReference") }
                                .addOnFailureListener { e -> println("Error adding document $e") }
                            Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra(NEW_USER, fName.text.toString())
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Sign up error", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

        backtoLogin = findViewById(R.id.back_to_login)
        backtoLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
