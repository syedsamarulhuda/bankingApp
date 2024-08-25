package com.bankingApp.transaction_history.repository

import com.bankingApp.core_common.data_model.Transactions
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryRepository {
     suspend fun getTransactions(): Flow<List<Transactions>>
}