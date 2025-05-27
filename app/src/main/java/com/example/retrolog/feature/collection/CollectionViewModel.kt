package com.example.retrolog.feature.collection

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.domain.usecase.account.DeleteFromFavoriteUseCase
import com.example.retrolog.domain.usecase.account.DeleteFromWatchListUseCase
import com.example.retrolog.domain.usecase.account.GetFavoriteFilmUseCase
import com.example.retrolog.domain.usecase.account.GetWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getFavoriteFilmUseCase: GetFavoriteFilmUseCase,
    private val getWatchListUseCase: GetWatchListUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val deleteFromWatchListUseCase: DeleteFromWatchListUseCase
): ViewModel() {

    private val _isLoading= mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _filmList= mutableStateOf<List<FilmData>>(emptyList())
    val filmList: State<List<FilmData>> = _filmList

    fun setLoadingState(state: Boolean) {
        _isLoading.value= state
    }

    fun setFilmList(data: List<FilmData>) {
        _filmList.value= data
    }

    fun getFavoriteFilm(type: String)= getFavoriteFilmUseCase(type)
    fun getWatchlist(type: String)= getWatchListUseCase(type)
    fun deleteFromFavorite(request: DeleteFromFavoriteRequest)= deleteFromFavoriteUseCase(request)
    fun deleteFromWatchlist(request: DeleteFromWatchlistRequest)= deleteFromWatchListUseCase(request)
}
