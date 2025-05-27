package com.example.retrolog.feature.detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.retrolog.feature.component.EmptyStateCard
import com.example.retrolog.feature.component.FilmCard
import com.example.retrolog.feature.detail.FilmDetailViewModel
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.util.Route

@Composable
fun FilmRecommendation(navController: NavController, viewModel: FilmDetailViewModel, type: String) {

    val filmRecommendation by viewModel.filmRecommendation

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Recommendations",
            fontSize = 18.sp,
            color = Neutral50,
            fontWeight = FontWeight.Bold
        )
        LazyRow(
            Modifier.fillMaxWidth(),
        ) {
            if (filmRecommendation.isEmpty()) {
                items(2) {
                    EmptyStateCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .padding(end = 16.dp)
                    )
                }
            } else {
                items(filmRecommendation) {
                    FilmCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(end = 16.dp),
                        onClick = {
                            navController.navigate(Route.FilmDetailScreen(type, it.id))
                        },
                        imagePath = it.poster_path
                    )
                }
            }
        }
    }
}