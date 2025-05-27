package com.example.retrolog.feature.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrolog.ui.theme.Primary700

@Composable
fun EmptyStateCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Primary700),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
    ) {

    }
}