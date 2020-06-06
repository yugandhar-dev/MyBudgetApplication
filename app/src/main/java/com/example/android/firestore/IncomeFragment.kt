package com.example.android.firestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.firestore.R.string
import com.example.android.firestore.models.*
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_income.*

class IncomeFragment : Fragment() {
    private lateinit var adapter: TransactionRecyclerAdapter
    val db = FirebaseFirestore.getInstance()
    val collectionRef = db.collection("users")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?= inflater.inflate(R.layout.fragment_income, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        val loginUser = FirebaseAuth.getInstance().currentUser

        if (loginUser != null) {
            val email = loginUser.email
            val emailVerified = loginUser.isEmailVerified
            val uid = loginUser.uid
            if (email != null) {
                val query: Query = collectionRef.document(email).collection("Income")

                val options = FirestoreRecyclerOptions.Builder<TransactionPost>()
                    .setQuery(query, TransactionPost::class.java)
                    .build()

                adapter = TransactionRecyclerAdapter(options)
                income_recyler_view.setHasFixedSize(true)
                val topSpacingDecor = SpacingTransactionList(10)
                income_recyler_view.addItemDecoration(topSpacingDecor)
                income_recyler_view.layoutManager = LinearLayoutManager(activity)
                income_recyler_view.adapter = adapter

                var totalIncome = 0.0
                collectionRef.document(email).collection("Income")
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            println("asdf ${document.id} => ${document.data["amount"]}")
                            totalIncome += document.data["amount"].toString().toDouble()

                        }
                        income_amount.text = totalIncome.toString() + " AUD"
                    }
                    .addOnFailureListener { exception ->
                        Log.w(HomeFragment.TAG, "Error getting documents: ", exception)
                    }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}