package com.zeller.transaction_history_impl.usecase

import com.zeller.transaction_history.repository.TransactionHistoryRepository
import com.zeller.transaction_history.usecase.TransactionHistoryUseCase

class TransactionHistoryUseCaseImpl(private val transactionHistoryRepository: TransactionHistoryRepository) :
    TransactionHistoryUseCase {

    override fun getTransactions() = transactionHistoryRepository.getTransactions()
}