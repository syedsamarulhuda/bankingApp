package com.zeller.transaction_history.usecase

import com.zeller.core_database.TransactionHistoryEntity
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryUseCase {
    fun getTransactions(): Flow<List<TransactionHistoryEntity>>
}