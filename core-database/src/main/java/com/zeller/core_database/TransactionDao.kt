package com.zeller.core_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionHistory(transactionHistoryEntity: TransactionHistoryEntity)

    @Query("SELECT * FROM transaction_history")
    fun getAllTransactions(): Flow<MutableList<TransactionHistoryEntity>>
}