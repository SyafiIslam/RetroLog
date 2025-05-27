package com.example.retrolog.feature.search.component

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.retrolog.feature.search.SearchViewModel
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary700

@Composable
fun SearchScreenTopBar(
    navController: NavController,
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String? = null,
    leadingIcon: ImageVector? = null,
) {
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

        CustomTextField(text, onValueChange, placeHolder, leadingIcon)
    }
}