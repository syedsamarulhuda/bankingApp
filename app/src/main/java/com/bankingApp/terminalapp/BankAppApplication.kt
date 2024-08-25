package com.bankingApp.terminalapp

import android.app.Application
import com.bankingApp.injector.di.KoinInjector

class BankAppApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInjector.initiateKoin(this)
    }
}