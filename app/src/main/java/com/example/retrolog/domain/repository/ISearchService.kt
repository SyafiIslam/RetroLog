package com.example.retrolog.domain.repository

import com.example.retrolog.data.remote.response.list.FilmListResponse

interface ISearchService {

    suspend fun browseMovies(query: String, type: String): FilmListResponse
}