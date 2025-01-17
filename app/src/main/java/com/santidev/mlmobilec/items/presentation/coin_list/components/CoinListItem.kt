package com.santidev.mlmobilec.items.presentation.coin_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.santidev.mlmobilec.items.presentation.models.ItemUi
import com.santidev.mlmobilec.items.presentation.models.toItemUi
import com.santidev.mlmobilec.items.domain.Item
import com.santidev.mlmobilec.ui.theme.MLMobileCTheme

@Composable
fun CoinListItem(
    itemUi: ItemUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(150.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(itemUi.url),
                contentDescription = itemUi.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(
                        ratio = 0.8f,
                        matchHeightConstraintsFirst = true
                    )
            )
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = itemUi.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "4.6",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = contentColor.copy(0.5f)
                )
                Row {
                    (1..5).forEach { it ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "(66)",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    color = contentColor.copy(0.5f)
                )
            }
            Text(
                text = "$ ${itemUi.originalPrice.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = contentColor.copy(0.6f),
                textDecoration = TextDecoration.LineThrough
            )
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
                    text = "34% OFF",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Green
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListItemPreview() {
    MLMobileCTheme {
        CoinListItem(
            itemUi = previewItem,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewItem = Item(
    id = "bitcoin",
    title = "Bitcoin",
    url = "https://es.digitaltrends.com/wp-content/uploads/2023/12/modelos-samsung.jpeg?fit=720%2C478&p=1",
    originalPrice = 1030000.0,
    price = 669900.0
).toItemUi()