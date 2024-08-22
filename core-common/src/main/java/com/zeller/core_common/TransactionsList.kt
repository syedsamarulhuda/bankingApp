package com.zeller.core_common

import com.zeller.core_common.data_model.Transactions

class TransactionsList {
    private val transactionsList: MutableList<Transactions> = mutableListOf()
    fun addTransaction(transactions: Transactions) {
        transactionsList.add(transactions)
    }

    fun getTransactions() = transactionsList
}