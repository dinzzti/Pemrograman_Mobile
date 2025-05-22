package com.example.viewmodel

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DramaViewModelFactory(private val resources: Resources) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DramaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DramaViewModel(resources) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
