package com.example.retrolog.feature.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.domain.usecase.search.BrowseFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val browseFilmUseCase: BrowseFilmUseCase,
): ViewModel() {

    private val _isLoading= mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _filmList= mutableStateOf<List<FilmData>>(emptyList())
    val filmList: State<List<FilmData>> = _filmList

    private val _searchTextField= mutableStateOf("")
    val searchTextField: State<String> = _searchTextField

    private val _filmType = mutableStateOf("Movies")
    val filmType: State<String> = _filmType

    fun setLoadingState(state: Boolean) {
        _isLoading.value= state
    }

    fun setFilmList(data: List<FilmData>) {
        _filmList.value= data
    }

    fun setSearchTextField(value: String) {
        _searchTextField.value= value
    }

    fun setFilmType(type: String) {
        _filmType.value= type
    }

    fun browseFilm(type: String, query: String) = browseFilmUseCase(type = type, query = query)
}