package com.santidev.mlmobilec.items.data.mappers

import com.santidev.mlmobilec.items.data.networking.dto.ItemDto
import com.santidev.mlmobilec.items.domain.Item

fun ItemDto.toItem(): Item {
    return Item(
        id = id,
        title = title ?: "Title Not Found",
        url = thumbnail ?: "",
        originalPrice = originalPrice ?: 0.0,
        price = price ?: 0.0,
        type = salePrice.type,
        seller = seller.nickname,
    )
}
