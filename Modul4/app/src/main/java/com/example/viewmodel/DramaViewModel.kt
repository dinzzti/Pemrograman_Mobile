package com.example.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DramaViewModel(private val resources: Resources) : ViewModel() {

    private val _dramaList = MutableStateFlow<List<Drama>>(emptyList())
    val dramaList: StateFlow<List<Drama>> get() = _dramaList

    private val _eventClick = MutableSharedFlow<Drama>()
    val eventClick: SharedFlow<Drama> get() = _eventClick

    init {
        loadDramas()
    }
    private fun getDramaFromResources(): List<Drama> {
        val dataTitle = resources.getStringArray(R.array.data_name)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataPlot = resources.getStringArray(R.array.data_plot)
        val dataYear = resources.getStringArray(R.array.data_year)
        val dataCast = resources.getStringArray(R.array.data_cast)

        return dataTitle.indices.map { i ->
            Drama(
                title = dataTitle[i],
                link = dataLink[i],
                photo = dataPhoto[i],
                plot = dataPlot[i],
                year = dataYear[i],
                cast = dataCast[i]
            )
        }
    }
    private fun loadDramas() {
        viewModelScope.launch {
            _dramaList.value = getDramaFromResources()
        }
    }
    fun onDramaClicked(drama: Drama) {
        viewModelScope.launch {
            _eventClick.emit(drama)
        }
    }
}
