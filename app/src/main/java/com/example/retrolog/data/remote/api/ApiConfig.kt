package com.example.retrolog.data.remote.api

import com.example.retrolog.data.remote.api.service.AccountService
import com.example.retrolog.data.remote.api.service.MovieService
import com.example.retrolog.data.remote.api.service.SearchService
import com.example.retrolog.data.remote.api.service.TvService
import com.example.retrolog.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val TOKEN = "Bearer ${Constant.TOKEN}"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Authorization", TOKEN)
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)

    val movieService: MovieService =
        retrofit
            .baseUrl(Constant.BASE_URL_MOVIE)
            .build()
            .create(MovieService::class.java)

    val tvService: TvService =
        retrofit
            .baseUrl(Constant.BASE_URL_TV)
            .build()
            .create(TvService::class.java)

    val accountService: AccountService =
        retrofit
            .baseUrl(Constant.BASE_URL_ACCOUNT)
            .build()
            .create(AccountService::class.java)

    val searchService: SearchService =
        retrofit
            .baseUrl(Constant.BASE_URL_SEARCH)
            .build()
            .create(SearchService::class.java)
}