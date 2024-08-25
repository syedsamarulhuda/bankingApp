package com.bankingApp.transaction_history_impl.usecase

import com.bankingApp.core_common.data_model.Transactions
import com.bankingApp.core_database.DatabaseClient
import com.bankingApp.transaction_history.repository.TransactionRepository

class TransactionRepositoryImpl(private val databaseClient: DatabaseClient) :
    TransactionRepository {
    override suspend fun doTransactions(transactions: Transactions) {
        databaseClient.insertTransaction(transactions)
    }
}