package com.zeller.transaction_history_impl.repository

import com.zeller.core_database.DatabaseClient
import com.zeller.transaction_history.repository.TransactionHistoryRepository

class TransactionHistoryRepositoryImpl(private val databaseClient: DatabaseClient) :
    TransactionHistoryRepository {

    override suspend fun getTransactions() = databaseClient.getAllTransactions()
}