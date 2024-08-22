package com.zeller.injector.di

import com.zeller.injector.database.getDatabaseModules
import com.zeller.injector.transaction_history.getTransactionHistoryModules
import org.koin.core.module.Module

fun appModule(): List<Module> {
    return getDatabaseModules() + getTransactionHistoryModules()
}