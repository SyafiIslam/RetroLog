package com.example.retrolog.domain.usecase.account

import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.repository.AccountRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(request: AddToFavoriteRequest): Flow<Resource<CollectionResponse>> =
        flow {

            emit(Resource.Loading())

            val result=
                try {
                    val response= repository.addToFavorite(request)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}