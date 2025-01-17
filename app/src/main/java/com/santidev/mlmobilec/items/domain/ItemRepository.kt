package com.santidev.mlmobilec.items.domain

import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.core.domain.util.Result

interface ItemRepository {
    suspend fun searchItems(
        query: String
    ): Result<List<Item>, DataError.Remote>

//    suspend fun getCoinHistory(
//        coinId: String,
//        start: ZonedDateTime,
//        end: ZonedDateTime
//    ): Result<List<CoinPrice>, DataError.Remote>
}