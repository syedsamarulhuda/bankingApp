package com.zeller.core_database

import android.content.Context
import com.zeller.core_common.data_model.Transactions

const val DB_NAME = "zeller_transaction_database"

class DatabaseClient(context: Context) {

    private val appDatabase: AppDatabase by lazy {
        Database(context = context).getAppDatabase(DB_NAME)
    }

    suspend fun insertTransaction(
        transactions: Transactions
    ) {
        appDatabase.transactionHistoryDao().insertTransactionHistory(
            TransactionHistoryEntity(
                transactionDate = transactions.timeStamp,
                amount = transactions.amount.toString(),
                isDeposit = transactions.isDeposit
            )
        )
    }

    fun getAllTransactions() = appDatabase.transactionHistoryDao().getAllTransactions()


}