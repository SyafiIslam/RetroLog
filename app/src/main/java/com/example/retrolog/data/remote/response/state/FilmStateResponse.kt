package com.example.retrolog.data.remote.response.state

data class FilmStateResponse(
    val favorite: Boolean,
    val id: Int,
    val rated: Boolean,
    val watchlist: Boolean
)