package com.lyapkov.translator.application

import android.app.Application
import com.lyapkov.translator.di.application
import com.lyapkov.translator.di.historyScreen
import com.lyapkov.translator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}