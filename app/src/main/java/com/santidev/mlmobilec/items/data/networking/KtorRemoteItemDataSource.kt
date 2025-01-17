package com.santidev.mlmobilec.items.data.networking

import com.santidev.mlmobilec.core.data.networking.constructUrl
import com.santidev.mlmobilec.core.data.networking.safeCall
import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.core.domain.util.map
import com.santidev.mlmobilec.items.data.mappers.toCoinPrice
import com.santidev.mlmobilec.items.data.networking.dto.CoinHistoryDto
import com.santidev.mlmobilec.items.data.networking.dto.ItemsResponseDto
import com.santidev.mlmobilec.items.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime
import com.santidev.mlmobilec.core.domain.util.Result

class KtorRemoteItemDataSource(
    private val httpClient: HttpClient
) : RemoteItemDataSource {

    override suspend fun searchItems(
        query: String
    ): Result<ItemsResponseDto, DataError.Remote> {
        return safeCall<ItemsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/search")
            ) {
                parameter("q", query)
            }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, DataError.Remote> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}