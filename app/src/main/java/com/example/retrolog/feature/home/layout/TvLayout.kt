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
import com.example.retrolog.feature.home.component.tv.AiringToday
import com.example.retrolog.feature.home.component.tv.OnTheAir
import com.example.retrolog.feature.home.component.tv.PopularShow
import com.example.retrolog.feature.home.component.tv.TopRatedShow

@Composable
fun TvLayout(navController: NavController, viewModel: HomeViewModel) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        item {
            AiringToday(navController, viewModel)
        }

        item {
            Spacer(Modifier.height(16.dp))
            PopularShow(navController, viewModel)
        }

        item {
            Spacer(Modifier.height(16.dp))
            OnTheAir(navController, viewModel)
        }

        item {
            Spacer(Modifier.height(16.dp))
            TopRatedShow(navController, viewModel)
        }
    }
}