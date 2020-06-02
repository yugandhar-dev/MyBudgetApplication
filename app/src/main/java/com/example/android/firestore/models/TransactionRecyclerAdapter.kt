package com.example.android.firestore.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.firestore.R
import kotlinx.android.synthetic.main.transaction_post_message.view.*

class TransactionRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = "AppDebug"

    private var items: List<TransactionPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_post_message, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TransactionViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(transactionList: List<TransactionPost>) {
        items = transactionList
    }

    class TransactionViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {
        val transactionImage = itemView.transaction_image
        val transactionTitle = itemView.transaction_title
        val transactionDate = itemView.transaction_date
        val transactionAmount = itemView.transaction_amount

        fun bind(transactionPost: TransactionPost) {
            transactionTitle.setText(transactionPost.title)
            transactionDate.setText(transactionPost.date)
            transactionAmount.setText(transactionPost.amount)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(transactionPost.image)
                .into(transactionImage)
        }
    }
}