package com.example.pokedex.di
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pokedex.features.HomeViewmodel
import org.koin.dsl.module


val viewModelHome = module {
    viewModel {
        HomeViewmodel()
    }
}