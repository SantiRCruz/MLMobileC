package com.santidev.mlmobilec.items.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String? = null,
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("original_price") val originalPrice: Double? = null,
    @SerialName("price") val price: Double? = null
)
