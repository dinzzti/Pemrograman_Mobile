package com.example.connectapi.domain.repository

import com.example.connectapi.domain.model.Drama
import com.example.connectapi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DramaRepository {
    fun getPopularDramas(): Flow<Resource<List<Drama>>>
}