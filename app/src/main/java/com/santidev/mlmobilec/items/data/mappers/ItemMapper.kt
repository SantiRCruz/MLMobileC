package com.santidev.mlmobilec.items.data.mappers

import com.santidev.mlmobilec.items.data.networking.dto.ItemDto
import com.santidev.mlmobilec.items.data.networking.dto.CoinPriceDto
import com.santidev.mlmobilec.items.domain.Item
import com.santidev.mlmobilec.items.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun ItemDto.toItem(): Item {
    return Item(
        id = id,
        title = title ?: "Title Not Found",
        url = thumbnail ?: "",
        originalPrice = originalPrice ?: 0.0,
        price = price ?: 0.0
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}