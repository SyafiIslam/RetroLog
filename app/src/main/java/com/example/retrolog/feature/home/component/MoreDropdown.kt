package com.example.retrolog.feature.home.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.util.Constant
import com.example.retrolog.util.Route

@Composable
fun MoreDropdown(modifier: Modifier= Modifier, navController: NavController, isExpanded: MutableState<Boolean>, onDismiss: () -> Unit) {

    val dropdownItem= listOf(
        stringResource(R.string.txt_favorite),
        stringResource(R.string.txt_watchlist)
    )

    DropdownMenu(
        expanded = isExpanded.value,
        onDismissRequest = { onDismiss() },
        modifier = modifier
    ) {
        dropdownItem.forEach {
            DropdownMenuItem(
                text = { Text(it) },
                onClick = {
                    isExpanded.value= false
                    navController.navigate(Route.CollectionScreen(
                        type = if (it == "Favorite") {
                            Constant.FAVORITE
                        } else{
                            Constant.WATCH_LIST
                        }
                    ))
                }
            )
        }
    }
}