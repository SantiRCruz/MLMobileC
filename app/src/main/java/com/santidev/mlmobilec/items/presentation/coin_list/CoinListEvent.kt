package com.santidev.mlmobilec.items.presentation.coin_list

import com.santidev.mlmobilec.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}