package com.example.retrolog.feature.home.layout

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.retrolog.feature.home.HomeViewModel
import com.example.retrolog.feature.home.component.movie.NowPlaying
import com.example.retrolog.feature.home.component.movie.PopularMovies
import com.example.retrolog.feature.home.component.movie.TopRatedMovie
import com.example.retrolog.feature.home.component.movie.UpcomingMovies

@Composable
fun MovieLayout(navController: NavController, viewModel: HomeViewModel) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        item {
            NowPlaying(navController, viewModel)
        }

        item {
            Spacer(Modifier.height(16.dp))
            PopularMovies(navController, viewModel)

        }

        item {
            Spacer(Modifier.height(16.dp))
            UpcomingMovies(navController, viewModel)
        }

        item {
            Spacer(Modifier.height(16.dp))
            TopRatedMovie(navController, viewModel)
        }
    }
}