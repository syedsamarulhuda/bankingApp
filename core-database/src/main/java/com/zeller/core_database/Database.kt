package com.zeller.core_database

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