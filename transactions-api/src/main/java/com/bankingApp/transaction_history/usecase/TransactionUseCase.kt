package com.bankingApp.transaction_history.usecase

import com.bankingApp.core_common.data_model.Transactions

interface TransactionUseCase {
    suspend fun doTransactions(transactions: Transactions)
}