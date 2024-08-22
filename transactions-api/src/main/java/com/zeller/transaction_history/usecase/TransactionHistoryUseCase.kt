package com.zeller.transaction_history.usecase

import com.zeller.core_common.data_model.Transactions
import kotlinx.coroutines.flow.Flow

interface TransactionHistoryUseCase {
   suspend fun getTransactions(): Flow<List<Transactions>>
}