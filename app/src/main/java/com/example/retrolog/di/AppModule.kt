package com.example.retrolog.di

import com.example.retrolog.data.remote.api.service.AccountService
import com.example.retrolog.data.remote.api.ApiConfig
import com.example.retrolog.data.remote.api.service.MovieService
import com.example.retrolog.data.remote.api.service.SearchService
import com.example.retrolog.data.remote.api.service.TvService
import com.example.retrolog.data.repository.AccountRepository
import com.example.retrolog.data.repository.MovieRepository
import com.example.retrolog.data.repository.SearchRepository
import com.example.retrolog.data.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieService()= ApiConfig.movieService

    @Provides
    @Singleton
    fun provideTvService()= ApiConfig.tvService

    @Provides
    @Singleton
    fun provideAccountService()= ApiConfig.accountService

    @Provides
    @Singleton
    fun provideSearchService()= ApiConfig.searchService

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieService)= MovieRepository(api)

    @Provides
    @Singleton
    fun provideTvRepository(api: TvService)= TvRepository(api)

    @Provides
    @Singleton
    fun provideAccountRepository(api: AccountService)= AccountRepository(api)

    @Provides
    @Singleton
    fun provideSearchRepository(api: SearchService)= SearchRepository(api)
}