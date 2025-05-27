package com.example.retrolog.data.repository

import android.os.Trace
import com.example.retrolog.data.remote.api.service.SearchService
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.domain.repository.ISearchService

class SearchRepository(private val api: SearchService) : ISearchService {
    override suspend fun browseMovies(query: String, type: String): FilmListResponse {
        Trace.beginSection("API: browseMovies")
        try {
            val response = api.browseFilm(type = type, query = query)

            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }

            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }
}
