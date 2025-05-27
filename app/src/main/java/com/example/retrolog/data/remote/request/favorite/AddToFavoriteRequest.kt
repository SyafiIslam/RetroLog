package com.example.retrolog.data.remote.request

data class AddToFavoriteRequest (
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean,
)