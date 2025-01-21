package com.santidev.mlmobilec.items.data.networking

import com.santidev.mlmobilec.core.data.networking.constructUrl
import com.santidev.mlmobilec.core.data.networking.safeCall
import com.santidev.mlmobilec.core.domain.util.DataError
import com.santidev.mlmobilec.items.data.networking.dto.ItemsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
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
}