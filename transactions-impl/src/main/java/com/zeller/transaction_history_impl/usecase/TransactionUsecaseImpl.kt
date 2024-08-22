package com.zeller.transaction_history_impl.usecase

import com.zeller.core_common.data_model.Transactions
import com.zeller.transaction_history.repository.TransactionRepository
import com.zeller.transaction_history.usecase.TransactionUseCase

class TransactionUseCaseImpl(private val transactionRepository: TransactionRepository) :
    TransactionUseCase {
    override suspend fun doTransactions(transactions: Transactions) =
        transactionRepository.doTransactions(transactions)
}