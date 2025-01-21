package com.santidev.mlmobilec.items.presentation.item_list

import androidx.compose.runtime.Immutable
import com.santidev.mlmobilec.core.presentation.UiText
import com.santidev.mlmobilec.items.presentation.models.ItemUi

@Immutable
data class ItemListState(
    val searchQuery: String = "Iphone",
    val isLoading: Boolean = false,
    val searchResults: List<ItemUi> = emptyList(),
    val selectedItem: ItemUi? = null,
    val errorMessage: UiText? = null
)
