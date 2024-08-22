package com.zeller.transaction_history.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeller.core_common.data_model.Transactions
import com.zeller.core_database.DatabaseClient
import com.zeller.transaction_history.usecase.TransactionHistoryUseCase
import com.zeller.transaction_history.usecase.TransactionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val transactionHistoryUseCase: TransactionHistoryUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    val transactions: MutableList<Transactions> = mutableListOf()

    fun getTransactionsHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionHistoryUseCase.getTransactions().collect {
                transactions.clear()
                it.forEach {
                    transactions.add(
                        Transactions(
                            isDeposit = it.isDeposit,
                            amount = it.amount.toBigDecimal(),
                            timeStamp = it.transactionDate
                        )
                    )
                }
            }
        }
    }

    fun insertTransaction(transactions: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionUseCase.doTransactions(transactions)
        }
    }

}