package com.santidev.mlmobilec.items.data.repository

import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.core.domain.util.Result
import com.santidev.mlmobilec.core.domain.util.map
import com.santidev.mlmobilec.items.data.mappers.toItem
import com.santidev.mlmobilec.items.data.networking.RemoteItemDataSource
import com.santidev.mlmobilec.items.domain.Item
import com.santidev.mlmobilec.items.domain.ItemRepository

class DefaultItemRepository(
    private val remoteItemDataSource: RemoteItemDataSource
) : ItemRepository {
    override suspend fun searchItems(
        query: String
    ): Result<List<Item>, DataError.Remote> {
        return remoteItemDataSource
            .searchItems(query)
            .map { dto ->
                dto.results.map { it.toItem() }
            }
    }
}