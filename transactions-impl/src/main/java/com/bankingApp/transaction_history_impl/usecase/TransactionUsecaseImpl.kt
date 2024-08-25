package com.bankingApp.transaction_history_impl.usecase

import com.bankingApp.core_common.data_model.Transactions
import com.bankingApp.transaction_history.repository.TransactionRepository
import com.bankingApp.transaction_history.usecase.TransactionUseCase

class TransactionUseCaseImpl(private val transactionRepository: TransactionRepository) :
    TransactionUseCase {
    override suspend fun doTransactions(transactions: Transactions) =
        transactionRepository.doTransactions(transactions)
}