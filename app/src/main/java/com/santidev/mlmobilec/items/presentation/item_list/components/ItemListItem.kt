package com.santidev.mlmobilec.items.presentation.item_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
fun ItemListItem(
    itemUi: ItemUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
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
    Row(
        modifier = modifier
            .height(170.dp)
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(),
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
            modifier = Modifier
                .fillMaxSize()
        ) {
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
private fun ItemListItemPreview() {
    MLMobileCTheme {
        ItemListItem(
            itemUi = previewItem,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewItem = Item(
    id = "1",
    title = "Samsung A05s 4g Dual Sim 128 Gb Negro 6 Gb Ram",
    url = "https://es.digitaltrends.com/wp-content/uploads/2023/12/modelos-samsung.jpeg?fit=720%2C478&p=1",
    originalPrice = 1030000.0,
    price = 669900.0,
    type = "Promotion",
    seller = "JDKA178"
).toItemUi()