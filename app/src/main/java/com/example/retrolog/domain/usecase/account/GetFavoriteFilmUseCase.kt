package com.example.retrolog.domain.usecase.account

import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.response.collection.CollectionResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.repository.AccountRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteFilmUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    operator fun invoke(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())

            val result=
                try {
                    val response= repository.getFavorite(type)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}