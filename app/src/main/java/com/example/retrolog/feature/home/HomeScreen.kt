package com.example.retrolog.feature.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.feature.home.component.SearchTopBar
import com.example.retrolog.feature.home.layout.MovieLayout
import com.example.retrolog.feature.home.layout.TvLayout
import com.example.retrolog.ui.theme.Neutral50
import com.example.retrolog.ui.theme.Neutral900
import com.example.retrolog.ui.theme.Primary300
import com.example.retrolog.ui.theme.Primary700
import com.example.retrolog.util.Constant
import com.example.retrolog.util.Resource
import com.example.retrolog.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel= hiltViewModel()
) {

    val context= LocalContext.current

    val tabTitle = listOf(
        stringResource(R.string.txt_movies),
        stringResource(R.string.txt_tv_shows)
    )

    var tabIndex by remember {
        mutableIntStateOf(0)
    }

    val isExpanded= remember {
        mutableStateOf(false)
    }

    val pagerState = rememberPagerState{ tabTitle.size}

    LaunchedEffect(true) {
        getNowPlayingMovies(viewModel, this, context)
        getPopularMovies(viewModel, this, context)
        getUpcomingMovies(viewModel, this, context)
        getTopRatedMovies(viewModel, this, context)

        getAiringTodayShows(viewModel, this, context)
        getPopularShows(viewModel, this, context)
        getOnTheAirShows(viewModel, this, context)
        getTopRatedShows(viewModel, this, context)
    }

    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        tabIndex= pagerState.currentPage
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        SearchTopBar(
            onSearchClick = {
                navController.navigate(Route.SearchScreen)
            },
            onMoreIconClick = {
                isExpanded.value= true
            },
            isExpanded,
            navController
        )
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.fillMaxWidth(),
            indicator = @Composable { tabPosition ->
                SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPosition[tabIndex])
                        .padding(horizontal = 35.dp)
                        .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                    height = 4.dp,
                    color = Primary700
                )
            }
        ) {
            tabTitle.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier.background(Neutral900),
                    selected = index == tabIndex,
                    onClick = {
                        tabIndex = index
                    },
                    text = {
                        Text(
                            text = tab,
                            fontWeight =
                            if (index == tabIndex) FontWeight.Bold
                            else FontWeight.Normal,
                        )
                    },
                    selectedContentColor = Primary300,
                    unselectedContentColor = Neutral50
                )
            }
        }

        HorizontalPager(state = pagerState) { index ->
            when (index) {
                0 -> MovieLayout(navController, viewModel)
                1 -> TvLayout(navController, viewModel)
            }
        }
    }
}

fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun getPopularMovies(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.POPULAR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setPopularMoviesList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getNowPlayingMovies(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.NOW_PLAYING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setNowPlayingMoviesList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getTopRatedMovies(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.TOP_RATED).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setTopRatedMovies(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getUpcomingMovies(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.UPCOMING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setUpcomingMovies(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getAiringTodayShows(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.NOW_AIRING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setAiringTodayShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getPopularShows(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.POPULAR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setPopularShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getOnTheAirShows(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.ON_THE_AIR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setOnTheAirShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getTopRatedShows(
    viewModel: HomeViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.TOP_RATED).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setTopRatedShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}