package com.example.retrolog.domain.repository

import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse

interface IMovieRepository {

    suspend fun getSpecificMovieType(type: String): FilmListResponse
    suspend fun getMovieDetail(id: Int): FilmDetailResponse
    suspend fun getMovieRecommendation(id: Int): FilmListResponse
    suspend fun getMovieTrailer(id: Int): FilmTrailerResponse
    suspend fun checkMovieState(id: Int): FilmStateResponse
    suspend fun getMovieReviews(id: Int): FilmReviewResponse
    suspend fun addMovieRating(id: Int, ratingRequest: RatingRequest): RatingResponse
}