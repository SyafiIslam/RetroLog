package com.example.retrolog.data.remote.request.watchlist

data class DeleteFromWatchlistRequest (
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean= false,
)