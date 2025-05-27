package com.example.retrolog.domain.usecase.tv

import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import com.example.retrolog.data.repository.TvRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShowTrailerUseCase @Inject constructor(
    private val repository: TvRepository
) {

    operator fun invoke(id: Int): Flow<Resource<FilmTrailerResponse>> =
        flow {
            emit(Resource.Loading())

            val result =
                try {
                    val response = repository.getTvTrailer(id)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}