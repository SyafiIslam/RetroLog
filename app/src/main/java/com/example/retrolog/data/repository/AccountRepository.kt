package com.example.retrolog.data.repository

import android.os.Trace
import com.example.retrolog.data.remote.api.service.AccountService
import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.domain.repository.IAccountRepository

class AccountRepository(private val api: AccountService) : IAccountRepository {

    override suspend fun addToFavorite(addToFavoriteRequest: AddToFavoriteRequest): CollectionResponse {
        Trace.beginSection("API: addToFavorite")
        try {
            val response = api.addToFavorite(addToFavoriteRequest = addToFavoriteRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun deleteFromFavorite(deleteFromFavoriteRequest: DeleteFromFavoriteRequest): CollectionResponse {
        Trace.beginSection("API: deleteFromFavorite")
        try {
            val response = api.deleteFromFavorite(deleteFromFavoriteRequest = deleteFromFavoriteRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun addToWatchlist(addToWatchlistRequest: AddToWatchlistRequest): CollectionResponse {
        Trace.beginSection("API: addToWatchlist")
        try {
            val response = api.addToWatchlist(addToWatchlistRequest = addToWatchlistRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun deleteFromWatchlist(deleteFromWatchlistRequest: DeleteFromWatchlistRequest): CollectionResponse {
        Trace.beginSection("API: deleteFromWatchlist")
        try {
            val response = api.deleteFromWatchlist(deleteFromWatchlistRequest = deleteFromWatchlistRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun getFavorite(type: String): FilmListResponse {
        Trace.beginSection("API: getFavorite $type")
        try {
            val response = api.getFavorite(type)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun getWatchlist(type: String): FilmListResponse {
        Trace.beginSection("API: getWatchlist $type")
        try {
            val response = api.getWatchlist(type)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }
}
