package com.lyapkov.translator.application

import android.app.Application
import com.lyapkov.translator.di.application
import com.lyapkov.translator.di.mainScreen
import org.koin.core.context.startKoin

class TranslatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}