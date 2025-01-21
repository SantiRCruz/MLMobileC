package com.santidev.mlmobilec.di

import com.santidev.mlmobilec.items.data.networking.KtorRemoteItemDataSource
import com.santidev.mlmobilec.items.data.repository.DefaultItemRepository
import com.santidev.mlmobilec.core.data.networking.HttpClientFactory
import com.santidev.mlmobilec.items.data.networking.RemoteItemDataSource
import com.santidev.mlmobilec.items.domain.ItemRepository
import io.ktor.client.engine.cio.CIO
import com.santidev.mlmobilec.items.presentation.item_list.ItemListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::KtorRemoteItemDataSource).bind<RemoteItemDataSource>()
    singleOf(::DefaultItemRepository).bind<ItemRepository>()
    viewModelOf(::ItemListViewModel)
}