package com.example.pokedex.di

import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pokedex.features.home.HomeViewModel
import com.example.pokedex.features.home.useCase.GetListPokemonUseCase
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.example.pokedex.repository.GetListPokemonRepository
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetListPokemonUseCaseInterface> {
        GetListPokemonUseCase(get())
    }
}

val viewModelHome = module {
    viewModel {
        HomeViewModel(get())
    }
}

val repositoryModule = module {
    single(createdAtStart = false) {
        GetListPokemonRepository()
    }
}