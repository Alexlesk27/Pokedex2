package com.example.pokedex.di

import com.example.pokedex.ApiRest.PokemonApi
import com.example.pokedex.ApiRest.repository.GetPokemonRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.pokedex.features.home.HomeViewModel
import com.example.pokedex.features.home.useCase.GetListPokemonUseCase
import com.example.pokedex.features.home.useCase.GetListPokemonUseCaseInterface
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val retrofitModule = module {
    factory<Interceptor> {
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger.DEFAULT
        ).setLevel(
            HttpLoggingInterceptor.Level.HEADERS
        )
    }
    factory {
        OkHttpClient.Builder().addInterceptor(
            interceptor = get()
        ).build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(PokemonApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}

val useCaseModule = module {
    factory<GetListPokemonUseCaseInterface> {
        GetListPokemonUseCase(get())
    }
}

val apiModule = module {
    single {
        get<Retrofit>().create(PokemonApi::class.java)
    }
}

val viewModelHome = module {
    viewModel {
        HomeViewModel(
            get(),
        )
    }
}

val repositoryModule = module {
    single{
        GetPokemonRepository(get())
    }
}