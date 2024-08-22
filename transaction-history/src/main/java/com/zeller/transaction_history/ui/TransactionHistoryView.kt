package com.zeller.transaction_history.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeller.transaction_history.R
import com.zeller.transaction_history.dataModel.Transactions


class TransactionHistoryView(context: Context, @Nullable attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private lateinit var adapter: TransactionHistoryAdapter
    private lateinit var rvTransactionHistory: RecyclerView

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.layout_transaction_history_view, this, true)
        init()
    }

    private fun init() {
        rvTransactionHistory = findViewById<View>(R.id.rv_transaction_history) as RecyclerView
        rvTransactionHistory.layoutManager = LinearLayoutManager(context)
    }

    fun updateTransactionHistory(transactionsList: MutableList<Transactions>) {
        adapter = TransactionHistoryAdapter(transactionsList)
        rvTransactionHistory.adapter = adapter
    }

}