package com.example.connectapi.data.repository

import android.util.Log
import com.example.connectapi.data.local.dao.DramaDao
import com.example.connectapi.data.local.entities.DramaEntity
import com.example.connectapi.data.remote.api.DramaApiService
import com.example.connectapi.domain.model.Drama
import com.example.connectapi.domain.repository.DramaRepository
import com.example.connectapi.utils.Resource
import com.example.connectapi.utils.toDomainModel
import com.example.connectapi.utils.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DramaRepositoryImpl(
    private val dramaDao: DramaDao,
    private val dramaApiService: DramaApiService,
    private val apiKey: String
) : DramaRepository {

    override fun getPopularDramas(): Flow<Resource<List<Drama>>> = flow {
        emit(Resource.Loading())

        val cachedDramasFlow = dramaDao.getAllDramas().map { entities ->
            entities.map { it.toDomainModel() }
        }
        val cachedDramas = cachedDramasFlow.firstOrNull()

        if (!cachedDramas.isNullOrEmpty()) {
            emit(Resource.Success(cachedDramas))
            Log.d("DramaRepositoryImpl", "Berhasil mengambil data dari database lokal (cached).")
        } else {
            Log.d("DramaRepositoryImpl", "Database lokal kosong atau tidak ada cached data.")
        }
        try {
            val response = dramaApiService.getPopularDramas(apiKey = apiKey)
            if (response.isSuccessful) {
                val dramaDtos = response.body()?.results ?: emptyList()
                val domainDramas = dramaDtos.map { it.toDomainModel() }

                withContext(Dispatchers.IO) {
                    dramaDao.deleteAllDramas()
                    dramaDao.insertDramas(dramaDtos.map { it.toEntity() })
                }
                Log.d("DramaRepositoryImpl", "Berhasil mengambil data dari API dan memperbarui database.")
                emit(Resource.Success(domainDramas))
            } else {
                val errorMessage = "API Error: ${response.code()} ${response.message()}"
                Log.e("DramaRepositoryImpl", errorMessage)
                emit(Resource.Error(errorMessage, cachedDramas))
            }
        } catch (e: HttpException) {
            val errorMessage = "Network Error (HTTP ${e.code()}): ${e.message()}"
            Log.e("DramaRepositoryImpl", errorMessage, e)
            emit(Resource.Error(errorMessage, cachedDramas))
        } catch (e: IOException) {
            val errorMessage = "No Internet Connection or API Timeout: ${e.message}"
            Log.e("DramaRepositoryImpl", errorMessage, e)
            emit(Resource.Error(errorMessage, cachedDramas)) // Sertakan cached data jika ada
        } catch (e: Exception) {
            val errorMessage = "An unexpected error occurred: ${e.localizedMessage}"
            Log.e("DramaRepositoryImpl", errorMessage, e)
            emit(Resource.Error(errorMessage, cachedDramas)) // Sertakan cached data jika ada
        }
    }
    suspend fun insertDramas(dramas: List<Drama>) {
        withContext(Dispatchers.IO) {
            dramaDao.insertDramas(dramas.map { it.toEntity() })
            Log.d("DramaRepositoryImpl", "Data drama berhasil dimasukkan ke database.")
        }
    }
    suspend fun clearAllDramas() {
        withContext(Dispatchers.IO) {
            dramaDao.deleteAllDramas()
            Log.d("DramaRepositoryImpl", "Semua data drama di database telah dihapus.")
        }
    }
}