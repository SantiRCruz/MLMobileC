package com.santidev.mlmobilec.items.presentation.item_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santidev.mlmobilec.items.presentation.item_detail.components.ImageCard
import com.santidev.mlmobilec.items.presentation.item_list.ItemListState
import com.santidev.mlmobilec.items.presentation.item_list.components.previewItem
import com.santidev.mlmobilec.ui.theme.MLMobileCTheme

@Composable
fun ItemDetailScreen(
    state: ItemListState,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedItem != null) {
        val itemUi = state.selectedItem
        val discountMessage = if (itemUi.hasDiscount) {
            "${itemUi.discount}% OFF"
        } else {
            "No Discount"
        }
        val discountColor = if (itemUi.hasDiscount) {
            Color.Green
        } else {
            MaterialTheme.colorScheme.errorContainer
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = itemUi.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor
            )
            ImageCard(
                title = itemUi.title,
                url = itemUi.url
            )
            Text(
                text = itemUi.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (itemUi.hasDiscount) {
                Text(
                    text = "$ ${itemUi.originalPrice.formatted}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = contentColor.copy(0.6f),
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "$ ${itemUi.price.formatted}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = contentColor
                )
                Text(
                    text = discountMessage,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = discountColor
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Seller: ${itemUi.seller}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor,
                )
                Text(
                    text = itemUi.type,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ItemDetailScreenPreview() {
    MLMobileCTheme {
        ItemDetailScreen(
            state = ItemListState(
                selectedItem = previewItem,
            ),
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}