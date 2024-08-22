package com.zeller.transaction_history.usecase

import com.zeller.core_common.data_model.Transactions

interface TransactionUseCase {
    suspend fun doTransactions(transactions: Transactions)
}