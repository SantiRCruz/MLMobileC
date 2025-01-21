package com.santidev.mlmobilec.items.data.networking.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SellerDto(
    @SerialName("nickname") val nickname: String
)
