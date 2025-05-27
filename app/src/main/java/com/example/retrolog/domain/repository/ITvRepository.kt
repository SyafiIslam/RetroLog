package com.example.retrolog.domain.repository

import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse

interface ITvRepository {

    suspend fun getSpecificTvType(type: String): FilmListResponse
    suspend fun getTvDetail(id: Int): FilmDetailResponse
    suspend fun getTvRecommendation(id: Int): FilmListResponse
    suspend fun getTvTrailer(id: Int): FilmTrailerResponse
    suspend fun checkShowState(id: Int): FilmStateResponse
    suspend fun getShowReviews(id: Int): FilmReviewResponse
    suspend fun addShowRating(id: Int, ratingRequest: RatingRequest): RatingResponse
}