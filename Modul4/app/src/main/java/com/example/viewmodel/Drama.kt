package com.example.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Drama(
    val title: String,
    val link: String,
    val photo: String,
    val plot: String,
    val year: String,
    val cast: String
) : Parcelable