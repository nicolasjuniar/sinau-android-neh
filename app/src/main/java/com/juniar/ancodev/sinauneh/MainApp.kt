package com.juniar.ancodev.sinauneh

import android.app.Application
import com.juniar.ancodev.sinauneh.module.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(listOf(NetworkModule.getModules()))
        }
    }
}