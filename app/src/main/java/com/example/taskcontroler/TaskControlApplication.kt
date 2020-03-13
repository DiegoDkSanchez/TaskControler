package com.example.taskcontroler

import android.app.Application
import com.example.taskcontroler.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskControlApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TaskControlApplication)
            androidLogger()
            modules(modules())
        }

    }

    open fun modules() = listOf(appModule)
}