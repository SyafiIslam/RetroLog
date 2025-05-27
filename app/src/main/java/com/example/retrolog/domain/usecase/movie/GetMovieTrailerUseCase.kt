package com.example.retrolog.domain.usecase.movie

import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int): Flow<Resource<FilmTrailerResponse>> =
        flow {
            emit(Resource.Loading())

            val result=
                try {
                    val response= repository.getMovieTrailer(id)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}