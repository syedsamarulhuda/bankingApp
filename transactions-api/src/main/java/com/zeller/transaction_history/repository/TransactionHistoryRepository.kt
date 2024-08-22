package com.zeller.transaction_history.repository

import com.zeller.core_database.TransactionHistoryEntity
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryRepository {
     fun getTransactions(): Flow<List<TransactionHistoryEntity>>
}