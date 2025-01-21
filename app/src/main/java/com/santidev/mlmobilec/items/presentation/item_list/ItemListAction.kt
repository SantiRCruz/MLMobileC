package com.santidev.mlmobilec.items.presentation.item_list

import com.santidev.mlmobilec.items.presentation.models.ItemUi

sealed interface ItemListAction {
    data class OnItemClick(val itemUi: ItemUi): ItemListAction
    data class OnSearchQueryChange(val query: String): ItemListAction
}