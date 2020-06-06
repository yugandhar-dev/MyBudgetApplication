package com.example.android.firestore.models

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.firestore.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class TransactionRecyclerAdapter(options: FirestoreRecyclerOptions<TransactionPost>) : FirestoreRecyclerAdapter<TransactionPost, TransactionRecyclerAdapter.TransactionHolder>(options) {
    override fun onBindViewHolder(holder: TransactionHolder, position: Int, model: TransactionPost) {
        holder.textViewDate?.setText(model.date)
        holder.textViewDescription?.setText(model.description)
        holder.textViewAmount?.setText(model.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.transaction_post_message,
            parent, false)
        return TransactionHolder(v)
    }

    inner class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewDate: TextView?
        var textViewDescription: TextView?
        var textViewAmount: TextView?

        init {
            textViewDate = itemView.findViewById(R.id.transaction_date)
            textViewDescription = itemView.findViewById(R.id.transaction_title)
            textViewAmount = itemView.findViewById(R.id.transaction_amount)
        }
    }
}