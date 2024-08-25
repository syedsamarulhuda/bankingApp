package com.bankingApp.transaction_history_impl.repository

import com.bankingApp.core_database.DatabaseClient
import com.bankingApp.transaction_history.repository.TransactionHistoryRepository

class TransactionHistoryRepositoryImpl(private val databaseClient: DatabaseClient) :
    TransactionHistoryRepository {

    override suspend fun getTransactions() = databaseClient.getAllTransactions()
}