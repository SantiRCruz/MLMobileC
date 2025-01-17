package com.santidev.mlmobilec.items.presentation.coin_list

import com.santidev.mlmobilec.items.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
    data class OnSearchQueryChange(val query: String): CoinListAction
}