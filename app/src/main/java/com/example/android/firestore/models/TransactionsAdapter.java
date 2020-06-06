package com.example.android.firestore.models;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.firestore.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TransactionsAdapter extends FirestoreRecyclerAdapter<TransactionPost, TransactionsAdapter.TransactionsHolder> {

    public TransactionsAdapter(@NonNull FirestoreRecyclerOptions<TransactionPost> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull TransactionsHolder holder, int position, @NonNull TransactionPost model) {
        holder.transactionTitle.setText(model.getDescription());
        holder.transactionAmount.setText(model.getAmount());
        holder.transactionDate.setText(model.getDate());

    }

    @NonNull
    @Override
    public TransactionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_post_message, parent, false);
        return new TransactionsHolder(v);
    }

    class TransactionsHolder extends RecyclerView.ViewHolder {

        TextView transactionTitle;
        TextView transactionDate;
        TextView transactionAmount;

        public TransactionsHolder(View itemView) {
            super(itemView);

            transactionTitle = itemView.findViewById(R.id.transaction_title);
            transactionDate = itemView.findViewById(R.id.transaction_date);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);
        }
    }
}
