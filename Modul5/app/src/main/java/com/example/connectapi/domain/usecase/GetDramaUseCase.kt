package com.example.connectapi.domain.usecase

import com.example.connectapi.domain.model.Drama
import com.example.connectapi.domain.repository.DramaRepository
import com.example.connectapi.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetDramaUseCase(private val dramaRepository: DramaRepository) {
    operator fun invoke(): Flow<Resource<List<Drama>>> {
        return dramaRepository.getPopularDramas()
    }
}