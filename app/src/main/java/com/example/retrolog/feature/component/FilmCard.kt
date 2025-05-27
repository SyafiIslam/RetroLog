package com.example.retrolog.feature.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrolog.ui.theme.Primary700

@Composable
fun FilmCard() {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(250.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Primary700),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        onClick = {}
    ) {

    }
}