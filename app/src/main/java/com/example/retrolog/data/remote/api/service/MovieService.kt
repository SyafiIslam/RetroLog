package com.example.retrolog.data.remote.api.service

import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieService {

    @GET("{type}")
    suspend fun getSpecificMovieType(
        @Path("type") type: String
    ): Response<FilmListResponse>

    @GET("{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
    ): Response<FilmDetailResponse>

    @GET("{id}/recommendations")
    suspend fun getMovieRecommendation(
        @Path("id") id: Int,
    ): Response<FilmListResponse>

    @GET("{id}/videos")
    suspend fun getMovieTrailer(
        @Path("id") id: Int,
    ): Response<FilmTrailerResponse>

    @GET("{id}/account_states")
    suspend fun checkMovieState(
        @Path("id") id: Int
    ): Response<FilmStateResponse>

    @GET("{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Int
    ): Response<FilmReviewResponse>

    @POST("{id}/rating")
    suspend fun addMovieRating(
        @Path("id") id: Int,
        @Body ratingRequest: RatingRequest
    ): Response<RatingResponse>
}