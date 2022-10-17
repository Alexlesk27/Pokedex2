package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.*
import com.example.pokedex.features.details.PokemonDetailsViewModel
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
                retrofitModule,
                viewModelHome,
                apiModule,
                useCaseModule,
                repositoryModule,
                viewModelPokemonDetails
            )
        }
    }
}