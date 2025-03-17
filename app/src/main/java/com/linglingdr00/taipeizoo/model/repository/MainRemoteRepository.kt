package com.linglingdr00.taipeizoo.model.repository

import android.util.Log
import com.linglingdr00.taipeizoo.model.data.response.AnimalResponse
import com.linglingdr00.taipeizoo.model.data.response.AreaResponse
import com.linglingdr00.taipeizoo.model.data.response.PlantResponse
import com.linglingdr00.taipeizoo.model.network.ZooApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainRemoteRepository {

    /** call api 獲取館區資料 */
    suspend fun getAreaData(): AreaResponse?

    /** call api 獲取植物資料 */
    suspend fun getPlantData(): PlantResponse?

    /** call api 獲取動物資料 */
    suspend fun getAnimalData(): AnimalResponse?

}

class MainRemoteRepositoryImpl @Inject constructor(
    private val zooApiService: ZooApiService
): MainRemoteRepository {

    companion object {
        private const val TAG = "MainRemoteRepositoryImpl"
    }

    override suspend fun getAreaData(): AreaResponse? {
        return withContext(Dispatchers.IO) {
            try {
                zooApiService.getAreaData()
            } catch (e: Exception) {
                Log.e(TAG, "getAreaData error: ${e.message}")
                null
            }
        }
    }

    override suspend fun getPlantData(): PlantResponse? {
        return withContext(Dispatchers.IO) {
            try {
                zooApiService.getPlantData()
            } catch (e: Exception) {
                Log.e(TAG, "getPlantData error: ${e.message}")
                null
            }
        }
    }

    override suspend fun getAnimalData(): AnimalResponse? {
        return withContext(Dispatchers.IO) {
            try {
                zooApiService.getAnimalData()
            } catch (e: Exception) {
                Log.e(TAG, "getAnimalData error: ${e.message}")
                null
            }
        }
    }

}