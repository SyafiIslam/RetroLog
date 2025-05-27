package com.example.retrolog.data.remote.api.service

import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {

    @POST("17134705/favorite")
    suspend fun addToFavorite(
        @Header("Content-Type") contentType: String = "application/json",
        @Body addToFavoriteRequest: AddToFavoriteRequest
    ): Response<CollectionResponse>

    @POST("17134705/favorite")
    suspend fun deleteFromFavorite(
        @Header("Content-Type") contentType: String = "application/json",
        @Body deleteFromFavoriteRequest: DeleteFromFavoriteRequest
    ): Response<CollectionResponse>

    @POST("17134705/watchlist")
    suspend fun addToWatchlist(
        @Header("Content-Type") contentType: String = "application/json",
        @Body addToWatchlistRequest: AddToWatchlistRequest
    ): Response<CollectionResponse>

    @POST("17134705/watchlist")
    suspend fun deleteFromWatchlist(
        @Header("Content-Type") contentType: String = "application/json",
        @Body deleteFromWatchlistRequest: DeleteFromWatchlistRequest
    ): Response<CollectionResponse>

    @GET("17134705/favorite/{type}")
    suspend fun getFavorite(
        @Path("type") type: String,
    ): Response<FilmListResponse>

    @GET("17134705/watchlist/{type}")
    suspend fun getWatchlist(
        @Path("type") type: String,
    ): Response<FilmListResponse>
}