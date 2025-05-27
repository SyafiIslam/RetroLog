package com.example.retrolog.domain.usecase.account

import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.repository.AccountRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToWatchListUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    operator fun invoke(request: AddToWatchlistRequest): Flow<Resource<CollectionResponse>> =
        flow {
            emit(Resource.Loading())

            val result=
                try {
                    val response= repository.addToWatchlist(request)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}