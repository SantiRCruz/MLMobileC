@file:OptIn(FlowPreview::class)

package com.santidev.mlmobilec.items.presentation.item_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santidev.mlmobilec.R
import com.santidev.mlmobilec.core.domain.util.onError
import com.santidev.mlmobilec.core.domain.util.onSuccess
import com.santidev.mlmobilec.core.presentation.UiText
import com.santidev.mlmobilec.core.presentation.toUiText
import com.santidev.mlmobilec.items.domain.ItemRepository
import com.santidev.mlmobilec.items.presentation.models.ItemUi
import com.santidev.mlmobilec.items.presentation.models.toItemUi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private var searchJob: Job? = null

    private val _state = MutableStateFlow(ItemListState())
    val state = _state
        .onStart {
            observeSearchQuery()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: ItemListAction) {
        when (action) {
            is ItemListAction.OnItemClick -> {
                selectItem(action.itemUi)
            }

            is ItemListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }

    private fun selectItem(itemUi: ItemUi) {
        _state.update { it.copy(selectedItem = itemUi) }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = UiText.StringResourceId(R.string.no_items_search),
                                searchResults = emptyList()
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchItems(query)
                    }
                }
            }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

        }
    }

    private fun searchItems(
        query: String
    ) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }

        itemRepository
            .searchItems(query)
            .onSuccess { searchResults ->
                _state.update {
                    it.copy(
                        errorMessage = null,
                        isLoading = false,
                        searchResults = searchResults.map { result ->
                            result.toItemUi()
                        }
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }
}