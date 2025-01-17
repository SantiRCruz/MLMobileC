package com.santidev.mlmobilec.items.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.santidev.mlmobilec.items.presentation.models.CoinUi

@Immutable
data class CoinListState(
    val searchQuery: String = "Iphone",
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi? = null
)
