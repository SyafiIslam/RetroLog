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

interface TvService {

    @GET("{type}")
    suspend fun getSpecificTvType(
        @Path("type") type: String,
    ): Response<FilmListResponse>

    @GET("{id}")
    suspend fun getTvDetail(
        @Path("id") id: Int,
    ): Response<FilmDetailResponse>

    @GET("{id}/recommendations")
    suspend fun getTvRecommendation(
        @Path("id") id: Int,
    ): Response<FilmListResponse>

    @GET("{id}/videos")
    suspend fun getTvTrailer(
        @Path("id") id: Int,
    ): Response<FilmTrailerResponse>

    @GET("{id}/account_states")
    suspend fun checkShowState(
        @Path("id") id: Int
    ): Response<FilmStateResponse>

    @GET("{id}/reviews")
    suspend fun getShowReviews(
        @Path("id") id: Int
    ): Response<FilmReviewResponse>

    @POST("{id}/rating")
    suspend fun addShowRating(
        @Path("id") id: Int,
        @Body ratingRequest: RatingRequest
    ): Response<RatingResponse>
}