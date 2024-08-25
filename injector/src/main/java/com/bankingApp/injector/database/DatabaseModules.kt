package com.bankingApp.injector.database

import com.bankingApp.core_database.DatabaseClient
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDatabaseModules(): List<Module> {

    val databaseClient = module {
        single { DatabaseClient(get()) }
    }
    return listOf(databaseClient)
}