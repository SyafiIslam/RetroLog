package com.example.retrolog.data.remote.response.review

data class FilmReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)