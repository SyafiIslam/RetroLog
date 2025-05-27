package com.example.retrolog.domain.usecase.movie

import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int): Flow<Resource<FilmDetailResponse>> =
        flow {
            emit(Resource.Loading())

            val result =
                try {
                    val response = repository.getMovieDetail(id)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}