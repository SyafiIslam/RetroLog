package com.example.retrolog.feature.search

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.retrolog.R
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.feature.component.FilmCard
import com.example.retrolog.feature.search.component.SearchScreenTopBar
import com.example.retrolog.ui.theme.Primary50
import com.example.retrolog.util.Resource
import com.example.retrolog.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val filmList by viewModel.filmList
    val filmType by viewModel.filmType

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val filmTypeList = listOf(
        stringResource(R.string.txt_movies),
        stringResource(R.string.txt_tv_shows)
    )

    LaunchedEffect(key1 = viewModel.searchTextField.value, key2 = viewModel.filmType.value) {
        val type =
            if (filmType == "Movies") "movie"
            else "tv"
        delay(500)
        browseFilm(viewModel, context, this, type, viewModel.searchTextField.value)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchScreenTopBar(
            navController = navController,
            text = viewModel.searchTextField.value,
            onValueChange = {
                viewModel.setSearchTextField(it)
            },
            placeHolder = stringResource(R.string.txt_place_holder_search_here),
            leadingIcon = Icons.Default.Search,
        )

        Spacer(Modifier.height(20.dp))
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            TextField(
                value = viewModel.filmType.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Primary50,
                    unfocusedIndicatorColor = Primary50,
                    focusedContainerColor = Primary50,
                    unfocusedContainerColor = Primary50
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(Primary50)
            ) {
                filmTypeList.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            viewModel.setFilmType(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            Modifier.padding(top = 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            if (filmList.isEmpty()) {
                item {

                }
            } else {
                items(filmList) {
                    FilmCard(
                        Modifier
                            .width(150.dp)
                            .padding(8.dp)
                            .wrapContentSize(Alignment.Center),
                        onClick = {
                            navController.navigate(
                                Route.FilmDetailScreen(
                                    if (filmType == "Movies") "movie"
                                    else "tv",
                                    it.id
                                )
                            )
                        },
                        imagePath = it.poster_path,
                        title =
                        if (filmType == "Movies") it.title ?: ""
                        else it.original_name ?: ""
                    )
                }
            }
        }
    }
}

private fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

private fun browseFilm(
    viewModel: SearchViewModel,
    context: Context,
    scope: CoroutineScope,
    type: String,
    query: String
) {
    scope.launch {
        viewModel.browseFilm(type = type, query = query).collect {
            when (it) {
                is Resource.Error -> {
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