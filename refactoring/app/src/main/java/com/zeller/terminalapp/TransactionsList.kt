package com.zeller.terminalapp

class TransactionsList {
    private val transactionsList: MutableList<Transactions> = mutableListOf()
    fun addTransaction(transactions: Transactions) {
        transactionsList.add(transactions)
    }
}