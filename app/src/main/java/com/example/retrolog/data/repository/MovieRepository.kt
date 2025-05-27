package com.example.retrolog.data.repository

import android.os.Trace
import com.example.retrolog.data.remote.api.service.MovieService
import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.remote.response.rating.RatingResponse
import com.example.retrolog.data.remote.response.review.FilmReviewResponse
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.data.remote.response.trailer.FilmTrailerResponse
import com.example.retrolog.domain.repository.IMovieRepository

class MovieRepository(
    private val api: MovieService
): IMovieRepository {
    override suspend fun getSpecificMovieType(type: String): FilmListResponse {
        Trace.beginSection("API: getSpecificMovieType")
        try {
            val response = api.getSpecificMovieType(type)
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

    override suspend fun getMovieDetail(id: Int): FilmDetailResponse {
        Trace.beginSection("API: getMovieDetail")
        try {
            val response = api.getMovieDetail(id)
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

    override suspend fun getMovieRecommendation(id: Int): FilmListResponse {
        Trace.beginSection("API: getMovieRecommendation")
        try {
            val response = api.getMovieRecommendation(id)
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

    override suspend fun getMovieTrailer(id: Int): FilmTrailerResponse {
        Trace.beginSection("API: getMovieTrailer")
        try {
            val response = api.getMovieTrailer(id)
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

    override suspend fun checkMovieState(id: Int): FilmStateResponse {
        Trace.beginSection("API: checkMovieState")
        try {
            val response = api.checkMovieState(id)
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

    override suspend fun getMovieReviews(id: Int): FilmReviewResponse {
        Trace.beginSection("API: getMovieReview")
        try {
            val response = api.getMovieReviews(id)
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

    override suspend fun addMovieRating(id: Int, ratingRequest: RatingRequest): RatingResponse {
        Trace.beginSection("API: addMovieRating")
        try {
            val response = api.addMovieRating(id, ratingRequest)
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