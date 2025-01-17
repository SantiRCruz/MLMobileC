package com.santidev.mlmobilec.items.data.networking

import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.core.domain.util.Result
import com.santidev.mlmobilec.items.data.networking.dto.ItemsResponseDto
import com.santidev.mlmobilec.items.domain.CoinPrice
import java.time.ZonedDateTime

interface RemoteItemDataSource {
    suspend fun searchItems(
        query: String
    ): Result<ItemsResponseDto, DataError.Remote>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, DataError.Remote>
}