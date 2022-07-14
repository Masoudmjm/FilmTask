package com.masoudjafari.filmnet.data.remote

import com.masoudjafari.filmnet.data.model.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.concurrent.TimeUnit


interface RetrofitService {

    @GET("video-contents")
    suspend fun getSearchResult(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("count") count: Int
    ): Response<SearchResponse>

    companion object {
        private val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(
                Interceptor { chain ->
                    val request: Request = chain.request().newBuilder()
                        .addHeader("X-Platform", "Android").build()
                    chain.proceed(request)
                })
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        private var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api-v2.filmnet.ir")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}