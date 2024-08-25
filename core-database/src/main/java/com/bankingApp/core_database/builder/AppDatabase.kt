package com.bankingApp.core_database.builder

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bankingApp.core_database.dao.TransactionHistoryDao
import com.bankingApp.core_database.entity.TransactionHistoryEntity

@Database(entities = [TransactionHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionHistoryDao(): TransactionHistoryDao
}