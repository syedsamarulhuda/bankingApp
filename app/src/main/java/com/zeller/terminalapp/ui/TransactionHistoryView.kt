package com.zeller.terminalapp.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeller.terminalapp.R
import com.zeller.transaction_history.viewModel.TransactionViewModel
import org.koin.java.KoinJavaComponent.inject


class TransactionHistoryView(context: Context, @Nullable attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private lateinit var adapter: com.zeller.terminalapp.ui.TransactionHistoryAdapter
    private lateinit var rvTransactionHistory: RecyclerView
    private val viewModel: TransactionViewModel by inject(TransactionViewModel::class.java)

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.layout_transaction_history_view, this, true)
        init()
        viewModel.getTransactionsHistory()
    }

    private fun init() {
        rvTransactionHistory = findViewById<View>(R.id.rv_transaction_history) as RecyclerView
        rvTransactionHistory.layoutManager = LinearLayoutManager(context)
        adapter = com.zeller.terminalapp.ui.TransactionHistoryAdapter(viewModel.transactions)
        rvTransactionHistory.adapter = adapter
    }
}