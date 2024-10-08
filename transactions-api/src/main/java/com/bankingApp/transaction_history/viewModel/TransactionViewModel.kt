package com.bankingApp.transaction_history.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bankingApp.core_common.data_model.Transactions
import com.bankingApp.core_common.util.getAmountWithCurrency
import com.bankingApp.transaction_history.usecase.TransactionHistoryUseCase
import com.bankingApp.transaction_history.usecase.TransactionUseCase
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
    var balance = 0.toBigDecimal()

    fun getTransactionsHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionHistoryUseCase.getTransactions().collect {
                transactions.clear()
                transactions.addAll(it)
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
            transactionHistoryUseCase.getTransactions().collect {
                setBalance(it)
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