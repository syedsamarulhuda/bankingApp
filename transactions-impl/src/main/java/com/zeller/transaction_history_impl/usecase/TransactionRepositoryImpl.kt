package com.zeller.transaction_history_impl.usecase

import com.zeller.core_common.data_model.Transactions
import com.zeller.core_database.DatabaseClient
import com.zeller.transaction_history.repository.TransactionRepository

class TransactionRepositoryImpl(private val databaseClient: DatabaseClient) :
    TransactionRepository {
    override suspend fun doTransactions(transactions: Transactions) {
        databaseClient.insertTransaction(transactions)
    }
}