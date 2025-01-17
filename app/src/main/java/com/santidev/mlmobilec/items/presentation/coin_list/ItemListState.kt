package com.santidev.mlmobilec.items.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.santidev.mlmobilec.core.presentation.UiText
import com.santidev.mlmobilec.items.presentation.models.ItemUi

@Immutable
data class ItemListState(
    val searchQuery: String = "Iphone",
    val isLoading: Boolean = false,
    val searchResults: List<ItemUi> = emptyList(),
    val selectedCoin: ItemUi? = null,
    val errorMessage: UiText? = null

)
