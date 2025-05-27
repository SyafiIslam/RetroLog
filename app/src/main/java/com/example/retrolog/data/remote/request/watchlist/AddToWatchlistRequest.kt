package com.example.retrolog.data.remote.request.watchlist

data class AddToWatchlistRequest (
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean= true,
)