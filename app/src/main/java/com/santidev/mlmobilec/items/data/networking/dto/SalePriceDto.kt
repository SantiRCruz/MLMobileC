package com.santidev.mlmobilec.items.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SalePriceDto(
    @SerialName("type") val type: String
)
