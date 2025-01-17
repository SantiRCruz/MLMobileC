package com.santidev.mlmobilec

import android.app.Application
import com.santidev.mlmobilec.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MLMobileCApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MLMobileCApp)
            androidLogger()

            modules(appModule)
        }
    }
}