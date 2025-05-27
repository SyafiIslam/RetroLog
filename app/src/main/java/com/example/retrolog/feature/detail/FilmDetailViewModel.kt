package com.example.retrolog.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.retrolog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.retrolog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.retrolog.data.remote.request.rating.RatingRequest
import com.example.retrolog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.retrolog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.retrolog.data.remote.response.detail.FilmDetailResponse
import com.example.retrolog.data.remote.response.list.FilmData
import com.example.retrolog.data.remote.response.review.Review
import com.example.retrolog.data.remote.response.state.FilmStateResponse
import com.example.retrolog.domain.usecase.account.AddToFavoriteUseCase
import com.example.retrolog.domain.usecase.account.AddToWatchListUseCase
import com.example.retrolog.domain.usecase.account.DeleteFromFavoriteUseCase
import com.example.retrolog.domain.usecase.account.DeleteFromWatchListUseCase
import com.example.retrolog.domain.usecase.movie.AddMovieRatingUseCase
import com.example.retrolog.domain.usecase.movie.CheckMovieStateUseCase
import com.example.retrolog.domain.usecase.movie.GetMovieDetailUseCase
import com.example.retrolog.domain.usecase.movie.GetMovieRecommendationUseCase
import com.example.retrolog.domain.usecase.movie.GetMovieReviewsUseCase
import com.example.retrolog.domain.usecase.movie.GetMovieTrailerUseCase
import com.example.retrolog.domain.usecase.tv.AddShowRatingUseCase
import com.example.retrolog.domain.usecase.tv.CheckShowStateUseCase
import com.example.retrolog.domain.usecase.tv.GetShowDetailUseCase
import com.example.retrolog.domain.usecase.tv.GetShowRecommendationUseCase
import com.example.retrolog.domain.usecase.tv.GetShowReviewsUseCase
import com.example.retrolog.domain.usecase.tv.GetShowTrailerUseCase
import com.example.retrolog.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getShowDetailUseCase: GetShowDetailUseCase,
    private val getMovieRecommendationUseCase: GetMovieRecommendationUseCase,
    private val getShowRecommendationUseCase: GetShowRecommendationUseCase,
    private val checkMovieStateUseCase: CheckMovieStateUseCase,
    private val checkShowStateUseCase: CheckShowStateUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val addToWatchListUseCase: AddToWatchListUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val deleteFromWatchListUseCase: DeleteFromWatchListUseCase,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    private val getShowTrailerUseCase: GetShowTrailerUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getShowReviewsUseCase: GetShowReviewsUseCase,
    private val addMovieRatingUseCase: AddMovieRatingUseCase,
    private val addShowRatingUseCase: AddShowRatingUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _filmDetail = mutableStateOf<FilmDetailResponse?>(null)
    val filmDetail: State<FilmDetailResponse?> = _filmDetail

    private val _filmRecommendation= mutableStateOf<List<FilmData>>(emptyList())
    val filmRecommendation: State<List<FilmData>> = _filmRecommendation

    private val _filmState= mutableStateOf<FilmStateResponse?>(null)
    val filmState: State<FilmStateResponse?> = _filmState

    private val _filmTrailer= mutableStateOf("")
    val filmTrailer: State<String> = _filmTrailer

    private val _userReview= mutableStateOf<List<Review>>(emptyList())
    val userReview: State<List<Review>> = _userReview

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun setFilmDetail(data: FilmDetailResponse) {
        _filmDetail.value = data
    }

    fun setFilmRecommendation(data: List<FilmData>) {
        _filmRecommendation.value= data
    }

    fun setFilmState(state: FilmStateResponse) {
        _filmState.value= state
    }

    fun getFilmDetail(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieDetailUseCase(id)
        } else {
            getShowDetailUseCase(id)
        }

    fun getFilmTrailer(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieTrailerUseCase(id)
        } else {
            getShowTrailerUseCase(id)
        }

    fun getUserReview(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieReviewsUseCase(id)
        } else {
            getShowReviewsUseCase(id)
        }

    fun addRating(id: Int, type: String, request: RatingRequest) =
        if (type == Constant.MOVIE) {
            addMovieRatingUseCase(id, request)
        } else {
            addShowRatingUseCase(id, request)
        }

    fun setFilmTrailer(trailer: String) {
        _filmTrailer.value= trailer
    }

    fun setUserReview(review: List<Review>) {
        _userReview.value= review
    }

    fun getMovieRecommendation(id: Int)= getMovieRecommendationUseCase(id)
    fun getShowRecommendation(id: Int)= getShowRecommendationUseCase(id)
    fun checkMovieStates(id: Int)= checkMovieStateUseCase(id)
    fun checkShowStates(id: Int)= checkShowStateUseCase(id)
    fun addToFavorite(request: AddToFavoriteRequest)= addToFavoriteUseCase(request)
    fun addToWatchlist(request: AddToWatchlistRequest)= addToWatchListUseCase(request)
    fun deleteFromFavorite(request: DeleteFromFavoriteRequest)= deleteFromFavoriteUseCase(request)
    fun deleteFromWatchlist(request: DeleteFromWatchlistRequest)= deleteFromWatchListUseCase(request)
}