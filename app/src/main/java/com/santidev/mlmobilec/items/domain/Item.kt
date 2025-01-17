package com.santidev.mlmobilec.items.domain

data class Item(
    val id: String,
    val title: String,
    val url: String,
    val originalPrice: Double,
    val price: Double
)
