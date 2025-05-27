package com.example.retrolog.feature.detail.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.util.Constant

@Composable
fun DetailHeader(
    navController: NavController,
    backdropPath: String?,
    filmTrailer: String,
) {

    val context= LocalContext.current

    Box(Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(
                    if (backdropPath == null) Primary700
                    else Color.Transparent
                )
        )

        backdropPath?.let {
            Box(
                modifier = Modifier.matchParentSize()
            ) {
                AsyncImage(
                    Constant.BASE_URL_IMAGE + it,
                    "background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                if (filmTrailer.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant
                                .TRAILER_URL + filmTrailer))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            "Trailer",
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Neutral50)
                                .padding(6.dp)
                        )
                    }
                }
            }
        }

        IconButton(
            onClick = { navController.popBackStack() },
            colors = IconButtonDefaults.iconButtonColors(containerColor = Neutral50),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                "",
            )
        }
    }
}