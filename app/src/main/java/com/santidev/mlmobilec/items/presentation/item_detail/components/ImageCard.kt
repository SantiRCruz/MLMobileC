package com.santidev.mlmobilec.items.presentation.item_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.santidev.mlmobilec.ui.theme.MLMobileCTheme

@Composable
fun ImageCard(
    title: String,
    url: String,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 35.dp,
                shape = RectangleShape,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            ),
        shape = RectangleShape,
        border = BorderStroke(
            width = 4.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = contentColor
        )
    ) {
        AnimatedContent(
            targetState = url,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            ),
            label = "UrlAnimation"
        ) { url ->
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(450.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun InfoCardPreview() {
    MLMobileCTheme {
        ImageCard(
            title = "Price",
            url = ""
        )
    }
}