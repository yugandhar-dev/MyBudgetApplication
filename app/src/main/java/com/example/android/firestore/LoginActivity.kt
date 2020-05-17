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
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


const val CURRENT_USER = "current user"

class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginBtn: Button
    private lateinit var linkSignup: TextView
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        loginEmail = findViewById(R.id.login_email)
        loginPassword = findViewById(R.id.login_password)
        loginBtn = findViewById(R.id.login_button)
        linkSignup = findViewById(R.id.link_to_signup)

        loginBtn.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPassword.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter email address and password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            db.collection("users").document(email)
                                .get()
                                .addOnSuccessListener { documentSnapshot ->
                                    var userFirstName = documentSnapshot.get("first").toString()
                                    Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT)
                                        .show()
                                    val intent = Intent(this, Dashboard::class.java).apply {
                                        putExtra(CURRENT_USER, userFirstName)
                                    }
                                    startActivity(intent)
                                    finish()
                                }


                        } else {
                            Toast.makeText(
                                this,
                                "Email address or password is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }

        linkSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth.currentUser
    }
}
