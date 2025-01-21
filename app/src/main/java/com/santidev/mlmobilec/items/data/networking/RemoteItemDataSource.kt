package com.santidev.mlmobilec.items.data.networking

import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.core.domain.util.Result
import com.santidev.mlmobilec.items.data.networking.dto.ItemsResponseDto

interface RemoteItemDataSource {
    suspend fun searchItems(
        query: String
    ): Result<ItemsResponseDto, DataError.Remote>

}