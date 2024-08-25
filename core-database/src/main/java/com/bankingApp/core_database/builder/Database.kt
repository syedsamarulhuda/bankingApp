package com.bankingApp.core_database.builder

import android.content.Context
import androidx.room.Room

class Database(private val context: Context) {

    fun getAppDatabase(databaseName: String): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }
}