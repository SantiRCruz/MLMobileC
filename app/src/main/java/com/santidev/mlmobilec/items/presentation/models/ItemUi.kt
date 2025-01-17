package com.santidev.mlmobilec.items.presentation.models

import android.icu.text.NumberFormat
import com.santidev.mlmobilec.items.domain.Item
import java.util.Locale

data class ItemUi(
    val id: String,
    val title: String,
    val url: String,
    val originalPrice: DisplayableNumber,
    val price: DisplayableNumber
)

data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Item.toItemUi(): ItemUi {
    return ItemUi(
        id = id,
        title = title,
        url = url,
        originalPrice = originalPrice.toDisplayableNumber(),
        price = price.toDisplayableNumber(),
//        iconRes = getDrawableIdForCoin(symbol)
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