package com.example.pokedex.di
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pokedex.features.home.HomeViewModel
import org.koin.dsl.module

val viewModelHome = module {
    viewModel {
        HomeViewModel()
    }
}