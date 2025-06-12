package com.example.connectapi.utils

import com.example.connectapi.data.local.entities.DramaEntity
import com.example.connectapi.data.remote.model.DramaDto
import com.example.connectapi.domain.model.Drama

fun DramaDto.toDomainModel(): Drama {
    return Drama(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )
}
fun DramaEntity.toDomainModel(): Drama {
    return Drama(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )
}
fun DramaDto.toEntity(): DramaEntity {
    return DramaEntity(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )
}
fun Drama.toEntity(): DramaEntity {
    return DramaEntity(
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        firstAirDate = firstAirDate
    )
}