package com.example.android.firestore.models

import java.util.*

data class TransactionPost(
    var title: String,
    var date: String,
    var amount: String,
    var image: String
) {
    override fun toString(): String {
        return "TransactionPost(title=$title, image=$image, date=$date, amount=$amount)"
    }
}