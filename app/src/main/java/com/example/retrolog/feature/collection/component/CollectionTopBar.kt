package com.example.retrolog.feature.collection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.util.Constant

@Composable
fun CollectionTopBar(navController: NavController, type: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .background(Primary700)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "",
                tint = Neutral50,
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = if (type == Constant.FAVORITE) {
                stringResource(R.string.txt_favorite)
            } else {
                stringResource(R.string.txt_watchlist)
            },
            color = Neutral50,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}