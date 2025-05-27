package com.example.retrolog.feature.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.retrolog.ui.theme.Primary50

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String? = null,
    leadingIcon: ImageVector? = null
) {
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth(),
        onValueChange = { onValueChange(it) },
        placeholder = {
            placeHolder?.let {
                Text(it)
            }
        },
        shape = CircleShape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Primary50,
            unfocusedContainerColor = Primary50,
            focusedIndicatorColor = Primary50,
            unfocusedIndicatorColor = Primary50,
        ),
        leadingIcon = {
            leadingIcon?.let {
                Icon(it, "")
            }
        },
    )
}