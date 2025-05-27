package com.example.retrolog.feature.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary700

@Composable
fun Fab(
    onClick: () -> Unit,
    isFavorite: MutableState<Boolean>
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        containerColor = Primary700
    ) {
        Icon(
            if (isFavorite.value) Icons.Outlined.Favorite
            else Icons.Outlined.FavoriteBorder,
            "Favorite",
            tint = Neutral50
        )
    }
}