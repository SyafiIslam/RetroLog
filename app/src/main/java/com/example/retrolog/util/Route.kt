package com.example.retrolog.util

import kotlinx.serialization.Serializable

object Route {

    @Serializable
    object HomeScreen

    @Serializable
    data class SeeAllScreen(
        val type: String,
        val category: String
    )

    @Serializable
    data class CollectionScreen(
        val type: String
    )

    @Serializable
    data class FilmDetailScreen(
        val type: String,
        val id: Int
    )

    @Serializable
    object SearchScreen
}