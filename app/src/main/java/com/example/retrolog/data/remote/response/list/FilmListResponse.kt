package com.example.retrolog.data.remote.response.list

data class FilmListResponse(
    val dates: Dates?= null,
    val page: Int,
    val results: List<FilmData>,
    val total_pages: Int,
    val total_results: Int
)