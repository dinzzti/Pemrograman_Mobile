package com.example.connectapi.data.remote

import android.util.Log
import com.example.connectapi.BuildConfig
import com.example.connectapi.data.remote.api.DramaApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val TAG = "ApiClient"

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
        coerceInputValues = true
    }

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        try {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            builder.addInterceptor(loggingInterceptor)
        } catch (e: NoClassDefFoundError) {
            Log.e(TAG, "HttpLoggingInterceptor class not found. This might be due to missing dependency.", e)
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing HttpLoggingInterceptor: ${e.message}", e)
        }

        builder.build()
    }

    val dramaApiService: DramaApiService by lazy {
        val apiKey: String
        try {
            apiKey = BuildConfig.TMDB_API_KEY
            Log.d(TAG, "DEBUG: TMDB_API_KEY yang DIBACA dari BuildConfig: '$apiKey'")

            if (apiKey.isNullOrEmpty() || apiKey == "null" || apiKey.contains("null", ignoreCase = true)) {
                Log.e(TAG, "FATAL ERROR: TMDB_API_KEY masih kosong atau 'null' saat runtime. Nilai yang dibaca: '$apiKey'")
                throw IllegalStateException("TMDB_API_KEY tidak ditemukan atau tidak valid di BuildConfig. Nilai yang dibaca: '$apiKey'")
            }
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(DramaApiService::class.java)
        } catch (e: IllegalStateException) {
            Log.e(TAG, "Error in ApiClient initialization due to invalid API Key: ${e.message}", e)
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing DramaApiService unexpectedly: ${e.message}", e)
            throw RuntimeException("Gagal menginisialisasi layanan API. Detail: ${e.localizedMessage}", e)
        }
    }
}