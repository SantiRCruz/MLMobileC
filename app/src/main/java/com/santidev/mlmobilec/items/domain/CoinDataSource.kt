package com.santidev.mlmobilec.items.domain

import com.santidev.mlmobilec.core.domain.util.NetworkError
import java.time.ZonedDateTime
import com.santidev.mlmobilec.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}