package com.example.retrolog.data.repository

import android.os.Trace
import com.example.retrolog.data.remote.api.service.TvService
import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import com.example.retrolog.domain.repository.ITvRepository

class TvRepository(private val api: TvService) : ITvRepository {

    override suspend fun getSpecificTvType(type: String): FilmListResponse {
        Trace.beginSection("API: getSpecificTvType")
        try {
            val response = api.getSpecificTvType(type)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }
    override suspend fun getTvDetail(id: Int): FilmDetailResponse {
        Trace.beginSection("API: getTvDetail")
        try {
            val response = api.getTvDetail(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun getTvRecommendation(id: Int): FilmListResponse {
        Trace.beginSection("API: getTvRecommendation")
        try {
            val response = api.getTvRecommendation(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun getTvTrailer(id: Int): FilmTrailerResponse {
        Trace.beginSection("API: getTvTrailer")
        try {
            val response = api.getTvTrailer(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun checkShowState(id: Int): FilmStateResponse {
        Trace.beginSection("API: checkShowState")
        try {
            val response = api.checkShowState(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun getShowReviews(id: Int): FilmReviewResponse {
        Trace.beginSection("API: getShowReviews")
        try {
            val response = api.getShowReviews(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }

    override suspend fun addShowRating(id: Int, ratingRequest: RatingRequest): RatingResponse {
        Trace.beginSection("API: addShowRating")
        try {
            val response = api.addShowRating(id, ratingRequest)
            if (response.isSuccessful) {
                response.body()?.let {
                    return it
                }
            }
            throw Exception(response.errorBody()?.string())
        } finally {
            Trace.endSection()
        }
    }
}
