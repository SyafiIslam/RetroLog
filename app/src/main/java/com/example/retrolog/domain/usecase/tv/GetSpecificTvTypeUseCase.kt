package com.example.retrolog.domain.usecase.tv

import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.repository.TvRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpecificTvTypeUseCase @Inject constructor(
    private val repository: TvRepository
) {

    operator fun invoke(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val response= repository.getSpecificTvType(type)
                emit(Resource.Success(response))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}