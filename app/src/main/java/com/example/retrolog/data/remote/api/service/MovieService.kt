package com.example.retrolog.data.remote.api

import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
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
}