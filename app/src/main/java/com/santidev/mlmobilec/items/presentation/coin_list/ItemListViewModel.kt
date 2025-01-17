@file:OptIn(FlowPreview::class)

package com.santidev.mlmobilec.items.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santidev.mlmobilec.core.domain.util.onError
import com.santidev.mlmobilec.core.domain.util.onSuccess
import com.santidev.mlmobilec.core.presentation.toUiText
import com.santidev.mlmobilec.items.domain.ItemRepository
import com.santidev.mlmobilec.items.presentation.models.ItemUi
import com.santidev.mlmobilec.items.presentation.models.toItemUi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ItemListViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private var cachedItems = emptyList<ItemUi>()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(ItemListState())
    val state = _state
        .onStart {
            if (cachedItems.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _events = Channel<CoinListEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.itemUi)
            }

            is CoinListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }

    private fun selectCoin(itemUi: ItemUi) {
        _state.update { it.copy(selectedCoin = itemUi) }

//        viewModelScope.launch {
//            itemRepository
//                .getCoinHistory(
//                    coinId = coinUi.id,
//                    start = ZonedDateTime.now().minusDays(5),
//                    end = ZonedDateTime.now()
//                )
//                .onSuccess { history ->
//                    val dataPoints = history
//                        .sortedBy { it.dateTime }
//                        .map {
//                            DataPoint(
//                                x = it.dateTime.hour.toFloat(),
//                                y = it.priceUsd.toFloat(),
//                                xLabel = DateTimeFormatter
//                                    .ofPattern("ha\nM/d")
//                                    .format(it.dateTime)
//                            )
//                        }
//
//                    _state.update {
//                        it.copy(
//                            selectedCoin = it.selectedCoin?.copy(
//                                coinPriceHistory = dataPoints
//                            )
//                        )
//                    }
//                }
//                .onError { error ->
//                    _events.send(CoinListEvent.Error(error))
//                }
//        }
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
                                errorMessage = null,
                                searchResults = cachedItems
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
                        isLoading = false,
                        searchResults = searchResults.map { it.toItemUi() }
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
                _events.send(CoinListEvent.Error(error))
            }
    }
}