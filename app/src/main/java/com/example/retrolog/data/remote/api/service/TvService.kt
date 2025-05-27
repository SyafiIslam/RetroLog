package com.example.retrolog.data.remote.api

import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
}