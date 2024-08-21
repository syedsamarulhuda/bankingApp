package com.zeller.terminalapp

import com.zeller.terminalapp.util.getAmountWithCurrency
import java.math.BigDecimal

object MainViewModel {
    private var balance = 0.toBigDecimal()
    var transactions: TransactionsList = TransactionsList()

    fun depositAmount(depositAmount: String?): String {
        if (!depositAmount.isNullOrEmpty()) {
            val amt = depositAmount.toString().toBigDecimal()
            balance += amt
            transactions.addTransaction(
                Transactions(
                    isDeposit = true,
                    amount = amt,
                    timeStamp = System.currentTimeMillis()
                )
            )
        }
        return getAmountWithCurrency(balance.toString())
    }

    fun withdrawAmount(withDrawAmount: String?): String {
        if (!withDrawAmount.isNullOrEmpty()) {
            val amt = withDrawAmount.toString().toBigDecimal()
            if (isAmountValidForWithDraw(amt)) {
                balance -= amt
                transactions.addTransaction(
                    Transactions(
                        isDeposit = false,
                        amount = amt,
                        timeStamp = System.currentTimeMillis()
                    )
                )
            }
        }
        return getAmountWithCurrency(balance.toString())
    }

    fun isAmountValidForWithDraw(withDrawAmount: BigDecimal?): Boolean {
        return balance > withDrawAmount
    }
}