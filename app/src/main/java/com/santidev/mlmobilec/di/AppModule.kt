package com.santidev.mlmobilec.di

import com.santidev.mlmobilec.items.data.networking.RemoteCoinDataSource
import com.santidev.mlmobilec.core.data.networking.HttpClientFactory
import com.santidev.mlmobilec.items.domain.CoinDataSource
import io.ktor.client.engine.cio.CIO
import com.santidev.mlmobilec.items.presentation.coin_list.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}