package com.example.retrolog.feature.home.component.tv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.feature.home.component.FilmCard
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.util.Constant
import com.example.retrolog.util.Route

@Composable
fun OnTheAir(navController: NavController) {
    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.txt_on_the_air),
                color = Neutral50,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.clickable {
                    navController.navigate(
                        Route.SeeAllScreen(
                            type = Constant.TV_SHOWS,
                            category = Constant.ON_THE_AIR
                        )
                    )
                },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    stringResource(R.string.txt_see_all),
                    color = Neutral50,
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = Neutral50
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        LazyRow(
            Modifier.fillMaxWidth(),
        ) {
            items(5) {
                FilmCard()
            }
        }
    }
}