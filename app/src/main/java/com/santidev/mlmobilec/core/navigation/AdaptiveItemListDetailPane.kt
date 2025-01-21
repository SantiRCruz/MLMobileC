@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.santidev.mlmobilec.core.navigation

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.santidev.mlmobilec.items.presentation.item_detail.ItemDetailScreen
import com.santidev.mlmobilec.items.presentation.item_list.ItemListAction
import com.santidev.mlmobilec.items.presentation.item_list.ItemListScreen
import com.santidev.mlmobilec.items.presentation.item_list.ItemListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdaptiveItemListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: ItemListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                ItemListScreen(
                    state = state,
                    onAction = { action ->
                        when (action) {
                            is ItemListAction.OnItemClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }

                            else -> Unit
                        }
                        viewModel.onAction(action)
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                ItemDetailScreen(state = state)
            }
        },
        modifier = modifier
    )
}