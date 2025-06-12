package com.example.connectapi.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connectapi.domain.model.Drama
import com.example.connectapi.domain.repository.DramaRepository
import com.example.connectapi.domain.usecase.GetDramaUseCase
import com.example.connectapi.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DramaViewModel(
    private val getDramaUseCase: GetDramaUseCase,
    private val dramaRepository: DramaRepository
) : ViewModel() {

    private val _popularDramasState = MutableStateFlow<Resource<List<Drama>>>(Resource.Loading())
    val popularDramasState: StateFlow<Resource<List<Drama>>> = _popularDramasState.asStateFlow()

    init {
        fetchPopularDramas()
    }
    private fun fetchPopularDramas() {
        viewModelScope.launch {
            getDramaUseCase().collect { resource ->
                _popularDramasState.value = resource
            }
        }
    }
    fun refreshDramasFromLocal() {
        fetchPopularDramas()
    }

    fun clearLocalData() {
        viewModelScope.launch {
            (dramaRepository as? com.example.connectapi.data.repository.DramaRepositoryImpl)?.clearAllDramas()
            fetchPopularDramas()
        }
    }
}