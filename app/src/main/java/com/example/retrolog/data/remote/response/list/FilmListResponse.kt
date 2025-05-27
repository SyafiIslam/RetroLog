package com.example.retrolog.data.remote.response.list.date_based

data class FilmListResponse(
    val dates: Dates?= null,
    val page: Int,
    val filmData: List<FilmData>,
    val total_pages: Int,
    val total_results: Int
)