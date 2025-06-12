package com.example.connectapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.connectapi.data.local.entities.DramaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DramaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDramas(dramas: List<DramaEntity>)

    @Query("SELECT * FROM dramas")
    fun getAllDramas(): Flow<List<DramaEntity>>

    @Query("DELETE FROM dramas")
    suspend fun deleteAllDramas()
}