package com.example.retrolog.data.remote.api

import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {

    @POST("{accountId}/favorite")
    fun addToFavorite(
        @Path("accountId") accountId: Int,
        @Body addToFavoriteRequest: AddToFavoriteRequest
    ): Response<CollectionResponse>

    @POST("{accountId}/favorite")
    fun deleteFromFavorite(
        @Path("accountId") accountId: Int,
        @Body deleteFromFavoriteRequest: DeleteFromFavoriteRequest
    ): Response<CollectionResponse>

    @POST("{accountId}/watchlist")
    suspend fun addToWatchlist(
        @Path("accountId") accountId: Int,
        @Body addToWatchlistRequest: AddToWatchlistRequest
    ): Response<CollectionResponse>

    @POST("{accountId}/watchlist")
    suspend fun deleteFromWatchlist(
        @Path("accountId") accountId: Int,
        @Body deleteFromWatchlistRequest: DeleteFromWatchlistRequest
    ): Response<CollectionResponse>

    @GET("{accountId}/favorite/{type}")
    suspend fun getFavorite(
        @Path("accountId") accountId: Int,
        @Path("type") type: Int,
    ): Response<FilmListResponse>

    @GET("{accountId}/watchlist/{type}")
    suspend fun getWatchlist(
        @Path("accountId") accountId: Int,
        @Path("type") type: Int,
    ): Response<FilmListResponse>
}