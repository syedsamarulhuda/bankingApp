package com.zeller.transaction_history.repository

import com.zeller.core_common.data_model.Transactions
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryRepository {
     suspend fun getTransactions(): Flow<List<Transactions>>
}