package com.example.retrolog.domain.usecase.movie

import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckMovieStateUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int): Flow<Resource<FilmStateResponse>> =
        flow {
            emit(Resource.Loading())

            val result=
                try {
                    val response= repository.checkMovieState(id)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}