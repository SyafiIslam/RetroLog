package com.example.retrolog.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.ui.theme.Primary50

@Composable
fun SearchTopBar(
    onSearchClick: () -> Unit,
    onMoreIconClick: () -> Unit,
    isExpanded: MutableState<Boolean>,
    navController: NavController,
) {
    Row(Modifier
        .fillMaxWidth()
        .clip(CircleShape)
        .background(Primary50)
        .clickable { onSearchClick() }
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                Icons.Default.Search,
                ""
            )
            Text(
                stringResource(R.string.txt_place_holder_search_here),
                fontSize = 16.sp
            )
        }

        Box {
            IconButton(
                onClick = { onMoreIconClick() },
                Modifier.size(24.dp)
            ) {
                Icon(Icons.Default.MoreVert, "")
            }

            MoreDropdown(
                modifier = Modifier
                    .background(Primary50),
                navController = navController,
                isExpanded = isExpanded,
                onDismiss = {
                    isExpanded.value = false
                }
            )
        }
    }
}