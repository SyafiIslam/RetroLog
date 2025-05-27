package com.example.retrolog.domain.usecase.movie

import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddMovieRatingUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int, ratingRequest: RatingRequest): Flow<Resource<RatingResponse>> =
        flow {
            emit(Resource.Loading())

            val result =
                try {
                    val response = repository.addMovieRating(id, ratingRequest)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}