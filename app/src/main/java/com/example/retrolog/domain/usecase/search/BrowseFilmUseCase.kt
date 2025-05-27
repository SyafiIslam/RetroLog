package com.example.retrolog.domain.usecase.search

import com.example.retrolog.data.remote.response.list.FilmListResponse
import com.example.retrolog.data.repository.SearchRepository
import com.example.retrolog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BrowseFilmUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    operator fun invoke(type: String, query: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())

            val result =
                try {
                    val response = repository.browseMovies(type= type, query = query)
                    Resource.Success(response)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                }

            emit(result)
        }
}