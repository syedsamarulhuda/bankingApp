package com.zeller.transaction_history.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeller.core_common.data_model.Transactions
import com.zeller.core_common.util.getAmountWithCurrency
import com.zeller.core_database.TransactionHistoryEntity
import com.zeller.transaction_history.usecase.TransactionHistoryUseCase
import com.zeller.transaction_history.usecase.TransactionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class TransactionViewModel(
    private val transactionHistoryUseCase: TransactionHistoryUseCase,
    private val transactionUseCase: TransactionUseCase
) : ViewModel() {

    var availableBalance = MutableStateFlow("")
    val transactions: MutableList<Transactions> = mutableListOf()
    private var balance = 0.toBigDecimal()

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

    fun getAvailableBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            val transactionList: MutableList<Transactions> = mutableListOf()
            transactionHistoryUseCase.getTransactions().collect {
                transactionList.clear()
                it.forEach {
                    transactionList.add(
                        Transactions(
                            isDeposit = it.isDeposit,
                            amount = it.amount.toBigDecimal(),
                            timeStamp = it.transactionDate
                        )
                    )
                }
                setBalance(transactionList)
            }

        }
    }

    private fun setBalance(transactions: List<Transactions>) {
        val creditedAmount = transactions.filter { it.isDeposit }.sumOf { it.amount }
        val debitedAmount = transactions.filter { !it.isDeposit }.sumOf { it.amount }
        balance = creditedAmount - debitedAmount
        this.availableBalance.value = getAmountWithCurrency(balance.toString())
    }

    fun isAmountValidForWithDraw(withDrawAmount: BigDecimal?): Boolean {
        return balance > withDrawAmount
    }

}