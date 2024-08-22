package com.zeller.terminalapp

import com.zeller.transaction_history.dataModel.Transactions

class TransactionsList {
    private val transactionsList: MutableList<Transactions> = mutableListOf()
    fun addTransaction(transactions: Transactions) {
        transactionsList.add(transactions)
    }

    fun getTransactions() = transactionsList
}