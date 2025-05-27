package com.example.retrolog.data.remote.request.favorite

data class DeleteFromFavoriteRequest (
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean= false,
)