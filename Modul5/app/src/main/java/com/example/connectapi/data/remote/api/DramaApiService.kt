package com.example.connectapi.data.remote.api

import com.example.connectapi.data.remote.model.PopularDramasResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DramaApiService {
    @GET("tv/popular")
    suspend fun getPopularDramas(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<PopularDramasResponse>
}