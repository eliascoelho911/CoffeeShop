package com.github.eliascoelho911.coffeeshop.presentation.root

import android.app.Application
import com.github.eliascoelho911.coffeeshop.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RootApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RootApplication)
            modules(appModule)
        }
    }
}