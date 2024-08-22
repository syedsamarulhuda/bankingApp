package com.zeller.core_database

import android.content.Context
import com.zeller.core_common.data_model.Transactions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    suspend fun getAllTransactions(): Flow<List<Transactions>> = flow {
        val transactionList: MutableList<Transactions> = mutableListOf()
        appDatabase.transactionHistoryDao().getAllTransactions().collect {
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
            emit(transactionList)
        }
    }


}