package com.zeller.terminalapp

import android.app.Application
import com.zeller.injector.di.KoinInjector

class ZellerApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInjector.initiateKoin(this)
    }
}