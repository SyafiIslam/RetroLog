package com.example.retrolog.data.remote.response.state

data class FilmStateResponse(
    val favorite: Boolean,
    val id: Int,
    val watchlist: Boolean
)