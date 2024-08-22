package com.zeller.core_database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "transaction_history")
data class TransactionHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val transactionDate: Long,
    val amount: String,
    val isDeposit: Boolean
)
