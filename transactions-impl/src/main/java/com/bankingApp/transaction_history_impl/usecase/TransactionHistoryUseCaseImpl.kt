package com.bankingApp.transaction_history_impl.usecase

import com.bankingApp.transaction_history.repository.TransactionHistoryRepository
import com.bankingApp.transaction_history.usecase.TransactionHistoryUseCase

class TransactionHistoryUseCaseImpl(private val transactionHistoryRepository: TransactionHistoryRepository) :
    TransactionHistoryUseCase {

    override suspend fun getTransactions() = transactionHistoryRepository.getTransactions()
}