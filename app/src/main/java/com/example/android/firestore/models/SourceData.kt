package com.example.android.firestore.models

class SourceData {
    companion object {

        fun createSourceDataSet(): ArrayList<TransactionPost>{
            var list = ArrayList<TransactionPost>()
            list.add(
                TransactionPost(
                    "McDonalds",
                    "22/03/2020",
                    "$23.00",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
                )
            )
            list.add(
                TransactionPost(
                    "Salary",
                    "29/03/2020",
                    "$2000.00",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/time_to_build_a_kotlin_app.png"
                )
            )
            list.add(
                TransactionPost(
                    "Shopping",
                    "04/04/2020",
                    "$123.00",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/coding_for_entrepreneurs.png"
                )
            )
            list.add(
                TransactionPost(
                    "Movies",
                    "10/04/2020",
                    "$203.00",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_vasiliy_zukanov.png"
                )
            )
            return list
        }
    }
}