package com.example.android.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Dashboard : AppCompatActivity() {

    private val fragmentManager = supportFragmentManager
    private val homeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.myFragment, homeFragment)
        fragmentTransaction.commit()
    }


}
