package com.example.retrolog.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.ui.theme.Neutral900
import com.example.retrolog.ui.theme.Primary50

@Composable
fun SearchBar(
    onSearchClick: () -> Unit,
    onMoreIconClick: () -> Unit,
    isExpanded: MutableState<Boolean>,
    navController: NavController
) {

    OutlinedTextField(
        value = "",
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSearchClick() },
        onValueChange = {},
        placeholder = {
            Text(stringResource(R.string.txt_place_holder_search_here))
        },
        readOnly = true,
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Primary50,
            unfocusedContainerColor = Primary50,
            cursorColor = Neutral900,
            focusedIndicatorColor = Neutral900,
            unfocusedIndicatorColor = Neutral900,
            focusedPlaceholderColor = Neutral900,
        ),
        leadingIcon = {
            Icon(Icons.Default.Search, "")
        },
        trailingIcon = {
            Box {
                IconButton(
                    onClick = { onMoreIconClick() }
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
    )
}