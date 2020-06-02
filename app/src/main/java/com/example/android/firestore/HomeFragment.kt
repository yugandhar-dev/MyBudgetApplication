package com.example.android.firestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.firestore.models.SourceData
import com.example.android.firestore.models.SpacingTransactionList
import com.example.android.firestore.models.TransactionRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private lateinit var transactionAdapter: TransactionRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecylerView()
        addDataSet()
    }

    private fun addDataSet() {
        val data = SourceData.createSourceDataSet()
        transactionAdapter.submitList(data)
    }

    private fun initRecylerView() {
        recyler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecor = SpacingTransactionList(30)
            addItemDecoration(topSpacingDecor)
            transactionAdapter = TransactionRecyclerAdapter()
            adapter = transactionAdapter
        }
    }

}