package com.bankingApp.injector.di

import com.bankingApp.injector.database.getDatabaseModules
import com.bankingApp.injector.transaction_history.getTransactionHistoryModules
import org.koin.core.module.Module

fun appModule(): List<Module> {
    return getDatabaseModules() + getTransactionHistoryModules()
}