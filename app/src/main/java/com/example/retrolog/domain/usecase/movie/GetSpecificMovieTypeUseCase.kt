package com.example.retrolog.domain.usecase.movie

import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpecificMovieTypeUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())

            val result= try {
                val response= repository.getSpecificMovieType(type)
                Resource.Success(response)
            } catch (e: Exception) {
                Resource.Error(e.message.toString())
            }

            emit(result)
        }
}