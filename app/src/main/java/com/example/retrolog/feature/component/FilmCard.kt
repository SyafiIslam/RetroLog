package com.example.retrolog.feature.component

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.util.Constant

@Composable
fun FilmCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imagePath: String = "",
    title: String = ""
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = { onClick() }
    ) {

        Column {
            AsyncImage(
                Constant.BASE_URL_IMAGE + imagePath,
                contentDescription = "Film Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
            )

            if (title.isNotEmpty()) {
                Text(
                    text = title,
                    color = Neutral50,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.width(100.dp).padding(4.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}