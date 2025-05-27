package com.example.retrolog.domain.repository

import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse

interface IAccountRepository {

    suspend fun addToFavorite(
        addToFavoriteRequest: AddToFavoriteRequest
    ): CollectionResponse

    suspend fun deleteFromFavorite(
        deleteFromFavoriteRequest: DeleteFromFavoriteRequest
    ): CollectionResponse

    suspend fun addToWatchlist(
        addToWatchlistRequest: AddToWatchlistRequest
    ): CollectionResponse

    suspend fun deleteFromWatchlist(
        deleteFromWatchlistRequest: DeleteFromWatchlistRequest
    ): CollectionResponse

    suspend fun getFavorite(
        type: String,
    ): FilmListResponse

    suspend fun getWatchlist(
        type: String,
    ): FilmListResponse
}