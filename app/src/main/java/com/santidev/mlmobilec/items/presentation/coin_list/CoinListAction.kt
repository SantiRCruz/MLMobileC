package com.santidev.mlmobilec.items.presentation.coin_list

import com.santidev.mlmobilec.items.presentation.models.ItemUi

sealed interface CoinListAction {
    data class OnCoinClick(val itemUi: ItemUi): CoinListAction
    data class OnSearchQueryChange(val query: String): CoinListAction
}