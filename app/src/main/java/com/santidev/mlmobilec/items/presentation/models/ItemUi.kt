package com.santidev.mlmobilec.items.presentation.models

import android.icu.text.NumberFormat
import com.santidev.mlmobilec.items.domain.Item
import java.util.Locale

data class ItemUi(
    val id: String,
    val title: String,
    val url: String,
    val originalPrice: DisplayableNumber,
    val price: DisplayableNumber,
    val discount: String,
    val hasDiscount: Boolean,
    val type: String,
    val seller: String
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Item.toItemUi(): ItemUi {
    return ItemUi(
        id = id,
        title = title,
        url = url.toHttps(),
        originalPrice = originalPrice.toDisplayableNumber(),
        price = price.toDisplayableNumber(),
        discount = calculateDiscountPercentage(originalPrice, discountedPrice = price),
        hasDiscount = hasDiscount(originalPrice, price),
        type = type,
        seller = seller
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun String.toHttps(): String {
    return if (this.startsWith("http://")) {
        this.replaceFirst("http://", "https://")
    } else {
        this
    }
}

fun calculateDiscountPercentage(originalPrice: Double, discountedPrice: Double): String {
    val discount = originalPrice - discountedPrice
    val discountPercentage = (discount / originalPrice) * 100
    return "%.2f".format(discountPercentage)
}

fun hasDiscount(originalPrice: Double, discountedPrice: Double): Boolean {
    return discountedPrice < originalPrice
}