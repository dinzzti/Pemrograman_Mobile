package com.example.connectapi.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drama(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val firstAirDate: String? 
) : Parcelable