package com.example.android.firestore.models

data class TransactionPost(
    var description: String,
    var date: String,
    var amount: String
) {

    constructor() : this("", "", "")

    override fun toString(): String {
        return "TransactionPost(description=$description, date=$date, amount=$amount)"
    }
}