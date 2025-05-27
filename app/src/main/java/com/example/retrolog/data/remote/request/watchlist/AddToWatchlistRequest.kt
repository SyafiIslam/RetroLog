package com.example.retrolog.data.remote.request.watchlist

data class AddToFavoriteRequest (
    val media_type: String,
    val media_id: Int,
    val watchLisy: Boolean= true,
)