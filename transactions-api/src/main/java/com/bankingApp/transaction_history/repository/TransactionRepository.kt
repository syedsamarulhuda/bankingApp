package com.bankingApp.transaction_history.repository

import com.bankingApp.core_common.data_model.Transactions

interface TransactionRepository {

    suspend fun doTransactions(transactions: Transactions)
}