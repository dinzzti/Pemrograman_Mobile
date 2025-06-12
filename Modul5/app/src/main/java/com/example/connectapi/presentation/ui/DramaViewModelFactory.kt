package com.example.connectapi.presentation.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.connectapi.data.local.Database.DramaDatabase
import com.example.connectapi.data.remote.ApiClient
import com.example.connectapi.data.repository.DramaRepositoryImpl
import com.example.connectapi.domain.usecase.GetDramaUseCase
import com.example.connectapi.BuildConfig

class DramaViewModelFactory(
    private val application: Application,
    private val getPopularDramaUseCase: GetDramaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DramaViewModel::class.java)) {

            val dramaDao = DramaDatabase.getDatabase(application).dramaDao()
            val dramaApiService = ApiClient.dramaApiService
            val apiKey = BuildConfig.TMDB_API_KEY
            val dramaRepository = DramaRepositoryImpl(dramaDao, dramaApiService, apiKey)
            val getDramaUseCase = GetDramaUseCase(dramaRepository)

            @Suppress("UNCHECKED_CAST")
            return DramaViewModel(getDramaUseCase, dramaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}