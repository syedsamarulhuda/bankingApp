package com.zeller.injector.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInjector {

    fun initiateKoin(context: Context) {
        startKoin {
            androidContext(context)
            modules(appModule())
        }
    }
}