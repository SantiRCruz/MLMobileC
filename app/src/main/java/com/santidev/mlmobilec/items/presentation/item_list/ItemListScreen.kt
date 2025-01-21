package com.santidev.mlmobilec.items.presentation.item_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.mlmobilec.items.presentation.item_list.components.ItemSearchBar
import com.santidev.mlmobilec.items.presentation.item_list.components.ItemListItem
import com.santidev.mlmobilec.items.presentation.item_list.components.previewItem
import com.santidev.mlmobilec.ui.theme.MLMobileCTheme

@Composable
fun ItemListScreen(
    state: ItemListState,
    onAction: (ItemListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(ItemListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        if (state.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.errorMessage != null) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage.asString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.searchResults) { itemUi ->
                    ItemListItem(
                        itemUi = itemUi,
                        onClick = {
                            onAction(ItemListAction.OnItemClick(itemUi))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ItemListScreenPreview() {
    MLMobileCTheme {
        ItemListScreen(
            state = ItemListState(
                searchResults = (1..100).map {
                    previewItem.copy(id = it.toString())
                }
            ),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background),
            onAction = {}
        )
    }
}