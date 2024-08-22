package com.zeller.transaction_history.repository

import com.zeller.core_common.data_model.Transactions

interface TransactionRepository {

    suspend fun doTransactions(transactions: Transactions)
}