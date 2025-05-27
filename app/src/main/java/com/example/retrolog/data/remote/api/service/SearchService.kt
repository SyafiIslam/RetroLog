package com.example.retrolog.data.remote.api

import com.example.retrolog.data.remote.response.list.FilmListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {

    @GET("{type}")
    fun browseFilm(
        @Path("type")
        @Query("query") query: String
    ): Response<FilmListResponse>
}