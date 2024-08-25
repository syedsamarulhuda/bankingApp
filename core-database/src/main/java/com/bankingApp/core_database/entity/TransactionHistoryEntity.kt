package com.bankingApp.core_database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_history")
data class TransactionHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val transactionDate: Long,
    val amount: String,
    val isDeposit: Boolean
)
