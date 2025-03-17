package com.linglingdr00.taipeizoo.model.repository

import android.util.Log
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.model.data.item.PlantItem
import com.linglingdr00.taipeizoo.model.data.response.AnimalResponse
import com.linglingdr00.taipeizoo.model.data.response.AreaResponse
import com.linglingdr00.taipeizoo.model.data.response.PlantResponse
import javax.inject.Inject

interface MainRepository {

    /** 獲取館區資料 */
    suspend fun getAreaItems(): List<AreaItem>

    /** 獲取植物資料 */
    suspend fun getPlantItems(): List<PlantItem>

    /** 獲取動物資料 */
    suspend fun getAnimalItems(): List<AnimalItem>

    /** 根據 area name 獲取植物資料 */
    suspend fun getPlantItemsByArea(areaName: String): List<PlantItem>

    /** 根據 area name 獲取動物資料 */
    suspend fun getAnimalItemsByArea(areaName: String): List<AnimalItem>
}

class MainRepositoryImpl @Inject constructor(
    private val mainLocalRepository: MainLocalRepository,
    private val mainRemoteRepository: MainRemoteRepository
): MainRepository {

    companion object {
        private const val TAG = "MainRepositoryImpl"
    }

    override suspend fun getAreaItems(): List<AreaItem> {
        return try {
            val areaData = mainRemoteRepository.getAreaData()
            if (areaData != null) {
                // 當 API 資料獲取成功時，將 areaData 轉成 areaItem
                val areaItems = areaDataToAreaItem(areaData)
                // 儲存資料到 local database
                mainLocalRepository.saveAreaItems(areaItems)
                areaItems
            } else {
                throw Exception("getAreaData error")
            }
            // 從 local database 獲取資料
            val areaItems = mainLocalRepository.getAreaItems()
            areaItems
        } catch (e: Exception) {
            Log.e(TAG, "getAreaItems remote: ${e.message}")
            // 當 API 資料獲取失敗時，從 local database 獲取資料
            val areaItems = mainLocalRepository.getAreaItems()
            areaItems
        }
    }

    override suspend fun getPlantItems(): List<PlantItem> {
        return try {
            val plantData = mainRemoteRepository.getPlantData()
            if (plantData != null) {
                // 當 API 資料獲取成功時，將 plantData 轉成 plantItem
                val plantItems = plantDataToPlantItem(plantData)
                // 儲存資料到 local database
                mainLocalRepository.savePlantItems(plantItems)
                plantItems
            } else {
                throw Exception("getPlantItems error")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getPlantItems remote: ${e.message}")
            // 當 API 資料獲取失敗時，從 local database 獲取資料
            val plantItems = mainLocalRepository.getPlantItems()
            plantItems
        }
    }

    override suspend fun getAnimalItems(): List<AnimalItem> {
        return try {
            val animalData = mainRemoteRepository.getAnimalData()
            if (animalData != null) {
                // 當 API 資料獲取成功時，將 animalData 轉成 animalItem
                val animalItems = animalDataToAnimalItem(animalData)
                // 儲存資料到 local database
                mainLocalRepository.saveAnimalItems(animalItems)
                animalItems
            } else {
                throw Exception("getAnimalItems error")
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAnimalItems remote: ${e.message}")
            // 當 API 資料獲取失敗時，從 local database 獲取資料
            val animalItems = mainLocalRepository.getAnimalItems()
            animalItems
        }
    }

    override suspend fun getPlantItemsByArea(areaName: String): List<PlantItem> {
        return try {
            mainLocalRepository.getPlantItemsByArea(areaName)
        } catch (e: Exception) {
            Log.e(TAG, "getPlantItemsByArea error: ${e.message}")
            listOf()
        }
    }

    override suspend fun getAnimalItemsByArea(areaName: String): List<AnimalItem> {
        return try {
            mainLocalRepository.getAnimalItemsByArea(areaName)
        } catch (e: Exception) {
            Log.e(TAG, "getAnimalItemsByArea error: ${e.message}")
            listOf()
        }
    }

    /** 將 area 資料轉換成 areaItem */
    private fun areaDataToAreaItem(areaData: AreaResponse): List<AreaItem> {
        val areaItems = mutableListOf<AreaItem>()
        areaData.result.results.forEach {
            val newAreaItem = AreaItem(
                id = it.id,
                eCategory = it.eCategory,
                eName = it.eName,
                ePicUrl = it.ePicUrl,
                eInfo = it.eInfo,
                eGeo = it.eGeo,
                eUrl = it.eUrl
            )
            areaItems.add(newAreaItem)
        }
        Log.d(TAG, "areaDataToAreaItem: $areaItems")
        return areaItems
    }

    /** 將 plant 資料轉換成 plantItem */
    private fun plantDataToPlantItem(plantData: PlantResponse): List<PlantItem> {
        val plantItems = mutableListOf<PlantItem>()
        plantData.result.results.forEach {
            val newPlantItem = PlantItem(
                id = it.id,
                fNameCh = it.fNameCh,
                fAlsoKnown = it.fAlsoKnown,
                fGeo = it.fGeo,
                fLocation = it.fLocation,
                fNameEn = it.fNameEn,
                fNameLatin = it.fNameLatin,
                fFamily = it.fFamily,
                fGenus = it.fGenus,
                fBrief = it.fBrief,
                fFeature = it.fFeature,
                fFunctionApplication = it.fFunctionApplication,
                fPic01Url = it.fPic01Url
            )
            plantItems.add(newPlantItem)
        }
        Log.d(TAG, "plantDataToPlantItem: $plantItems")
        return plantItems
    }

    /** 將 animal 資料轉換成 animalItem */
    private fun animalDataToAnimalItem(animalData: AnimalResponse): List<AnimalItem> {
        val animalItems = mutableListOf<AnimalItem>()
        animalData.result.results.forEach {
            val newAnimalItem = AnimalItem(
                id = it.id,
                aNameCh = it.aNameCh,
                aAlsoKnown = it.aAlsoKnown,
                aGeo = it.aGeo,
                aLocation = it.aLocation,
                aNameEn = it.aNameEn,
                aNameLatin = it.aNameLatin,
                aPhylum = it.aPhylum,
                aClass = it.aClass,
                aOrder = it.aOrder,
                aFamily = it.aFamily,
                aConservation = it.aConservation,
                aDistribution = it.aDistribution,
                aHabitat = it.aHabitat,
                aFeature = it.aFeature,
                aBehavior = it.aBehavior,
                aDiet = it.aDiet,
                aCrisis = it.aCrisis,
                aThemeName = it.aThemeName,
                aPic01Url = it.aPic01Url,
                aPic02Url = it.aPic02Url,
                aPic03Url = it.aPic03Url,
                aPic04Url = it.aPic04Url
            )
            animalItems.add(newAnimalItem)
        }
        Log.d(TAG, "animalDataToAnimalItem: $animalItems")
        return animalItems
    }

}