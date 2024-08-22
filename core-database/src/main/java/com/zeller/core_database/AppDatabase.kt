package com.zeller.core_database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TransactionHistoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionHistoryDao(): TransactionHistoryDao
}