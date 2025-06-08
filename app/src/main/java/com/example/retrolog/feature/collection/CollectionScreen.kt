package com.example.retrolog.feature.collection

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.feature.collection.component.CollectionItemCard
import com.example.retrolog.feature.collection.component.CollectionTopBar
import com.example.retrolog.feature.component.LoadingDialog
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
fun CollectionScreen(
    navController: NavController,
    type: String,
    viewModel: CollectionViewModel= hiltViewModel()
) {

    val context= LocalContext.current
    val scope= rememberCoroutineScope()

    val isLoading by viewModel.isLoading
    val filmList by viewModel.filmList

    val tabTitle = listOf(
        stringResource(R.string.txt_movies),
        stringResource(R.string.txt_tv_shows)
    )

    var tabIndex by remember {
        mutableIntStateOf(0)
    }

    var isReload by remember {
        mutableStateOf(false)
    }

    val pagerState = rememberPagerState{ tabTitle.size}

    LaunchedEffect(key1 = true, key2 = tabIndex, key3 = isReload) {
        if (type == Constant.FAVORITE) {
            when (tabIndex) {
                0 -> getFavoriteFilm(viewModel, this, context, Constant.MOVIE + "s")
                1 -> getFavoriteFilm(viewModel, this, context, Constant.TV)
            }
        } else {
            when (tabIndex) {
                0 -> getWatchlist(viewModel, this, context, Constant.MOVIE + "s")
                1 -> getWatchlist(viewModel, this, context, Constant.TV)
            }
        }
    }

    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        tabIndex= pagerState.currentPage
    }

    if (isLoading) {
        LoadingDialog()
    }

    Column(Modifier.fillMaxSize()) {
        CollectionTopBar(navController, type)

        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
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

        HorizontalPager(state = pagerState) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp)
            ) {
                items(filmList) {
                    CollectionItemCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        title =
                            if (tabIndex == 1)
                                it.original_name ?: ""
                            else
                                it.original_title ?: "",
                        posterPath = it.poster_path ?: "",
                        releaseData = if (tabIndex == 1)
                            it.first_air_date ?: ""
                        else
                            it.release_date ?: "",
                        onClick = {
                            navController.navigate(Route.FilmDetailScreen(
                                if (tabIndex == 1)
                                    Constant.TV
                                else
                                    Constant.MOVIE,
                                it.id
                            ))
                        },
                        onIconClick = {
                            if (type == Constant.FAVORITE) {
                                when (tabIndex) {
                                    0 -> deleteFromFavorite(
                                        viewModel,
                                        scope,
                                        context,
                                        it.id,
                                        Constant.MOVIE
                                    )
                                    1 -> deleteFromFavorite(
                                        viewModel,
                                        scope,
                                        context,
                                        it.id,
                                        Constant.TV
                                    )
                                }
                            } else {
                                when (tabIndex) {
                                    0 -> deleteFromWatchlist(
                                        viewModel,
                                        scope,
                                        context, it.id,
                                        Constant.MOVIE
                                    )
                                    1 -> deleteFromWatchlist(
                                        viewModel,
                                        scope,
                                        context,
                                        it.id,
                                        Constant.TV
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun getFavoriteFilm(
    viewModel: CollectionViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
    type: String
) {
    coroutineScope.launch {
        viewModel.getFavoriteFilm(type).collect {
            Log.i("@@@", "getFavoriteFilm: $type")
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setFilmList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

fun getWatchlist(
    viewModel: CollectionViewModel,
    coroutineScope: CoroutineScope,
    context: Context,
    type: String
) {
    coroutineScope.launch {
        viewModel.getWatchlist(type).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }
                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "getWatchlist $type: ${it.data?.results?.size}")
                    viewModel.setFilmList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun deleteFromFavorite(
    viewModel: CollectionViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = DeleteFromFavoriteRequest(
        media_type = type,
        media_id = id,
        favorite = false
    )
    scope.launch {

        viewModel.deleteFromFavorite(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    if (it.data?.success as Boolean) {
                        if (type == Constant.MOVIE) {
                            getFavoriteFilm(viewModel, this, context, type + "s")
                        } else {
                            getFavoriteFilm(viewModel, this, context, type)
                        }
                        showToast("Deleted from favorite", context)
                    } else {
                        val errorMessage= it.data.status_message
                        showToast(errorMessage, context)
                    }
                }
            }
        }
    }
}

private fun deleteFromWatchlist(
    viewModel: CollectionViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = DeleteFromWatchlistRequest(
        media_type = type,
        media_id = id,
        watchlist = false
    )
    scope.launch {
        viewModel.deleteFromWatchlist(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.toString(), context)Ser
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    if (it.data?.success as Boolean) {
                        if (type == Constant.MOVIE) {
                            getWatchlist(viewModel, this, context, type + "s")
                        } else {
                            getWatchlist(viewModel, this, context, type)
                        }
                        showToast("Deleted from watchlist", context)
                    } else {
                        val errorMessage= it.data.status_message
                        showToast(errorMessage, context)
                    }
                }
            }
        }
    }
}