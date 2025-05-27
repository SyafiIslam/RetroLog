package com.example.retrolog.domain.usecase.tv

import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.repository.TvRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShowReviewsUseCase @Inject constructor(
    private val repository: TvRepository
) {

    operator fun invoke(id: Int): Flow<Resource<FilmReviewResponse>> =
        flow {
            emit(Resource.Loading())

            val result =
                try {
                    val response = repository.getShowReviews(id)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}