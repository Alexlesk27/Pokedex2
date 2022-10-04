package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.repositoryModule
import com.example.pokedex.di.useCaseModule
import com.example.pokedex.di.viewModelHome
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class Myapp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Myapp)
            modules(
                viewModelHome,
                useCaseModule,
                repositoryModule
            )
        }
    }
}