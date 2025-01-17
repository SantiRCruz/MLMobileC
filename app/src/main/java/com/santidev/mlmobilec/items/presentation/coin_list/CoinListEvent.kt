package com.santidev.mlmobilec.items.presentation.coin_list

import com.santidev.mlmobilec.core.domain.util.DataError

sealed interface CoinListEvent {
    data class Error(val error: DataError) : CoinListEvent
}