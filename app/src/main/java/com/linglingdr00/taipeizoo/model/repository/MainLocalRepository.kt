package com.linglingdr00.taipeizoo.model.repository

import android.util.Log
import com.linglingdr00.taipeizoo.model.data.dao.AnimalDao
import com.linglingdr00.taipeizoo.model.data.dao.AreaDao
import com.linglingdr00.taipeizoo.model.data.dao.PlantDao
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.model.data.item.PlantItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface MainLocalRepository {

    /** 將館區資料儲存到 db */
    suspend fun saveAreaItems(areaItems: List<AreaItem>)

    /** 將植物資料儲存到 db */
    suspend fun savePlantItems(plantItems: List<PlantItem>)

    /** 將動物資料儲存到 db */
    suspend fun saveAnimalItems(animalItems: List<AnimalItem>)

    /** 從 db 獲取館區資料 */
    suspend fun getAreaItems(): List<AreaItem>

    /** 從 db 獲取植物資料 */
    suspend fun getPlantItems(): List<PlantItem>

    /** 從 db 獲取動物資料 */
    suspend fun getAnimalItems(): List<AnimalItem>

    /** 根據 area name 獲取植物資料 */
    suspend fun getPlantItemsByArea(areaName: String): List<PlantItem>

    /** 根據 area name 獲取動物資料 */
    suspend fun getAnimalItemsByArea(areaName: String): List<AnimalItem>

}

class MainLocalRepositoryImpl @Inject constructor(
    private val areaDao: AreaDao,
    private val plantDao: PlantDao,
    private val animalDao: AnimalDao
): MainLocalRepository {

    companion object {
        private const val TAG = "MainLocalRepositoryImpl"
    }

    override suspend fun saveAreaItems(areaItems: List<AreaItem>) {
        withContext(Dispatchers.IO) {
            try {
                areaDao.insertAreaItems(areaItems)
            } catch (e: Exception) {
                Log.e(TAG, "saveAreaItems error: ${e.message}")
            }
        }
    }

    override suspend fun savePlantItems(plantItems: List<PlantItem>) {
        withContext(Dispatchers.IO) {
            try {
                plantDao.insertPlantItems(plantItems)
            } catch (e: Exception) {
                Log.e(TAG, "savePlantItems error: ${e.message}")
            }
        }
    }

    override suspend fun saveAnimalItems(animalItems: List<AnimalItem>) {
        withContext(Dispatchers.IO) {
            try {
                animalDao.insertAnimalItems(animalItems)
            } catch (e: Exception) {
                Log.e(TAG, "saveAnimalItems error: ${e.message}")
            }
        }
    }

    override suspend fun getAreaItems(): List<AreaItem> {
        return withContext(Dispatchers.IO) {
            try {
                areaDao.getAreaItems()
            } catch (e: Exception) {
                Log.e(TAG, "getAreaItems error: ${e.message}")
                listOf()
            }
        }

    }

    override suspend fun getPlantItems(): List<PlantItem> {
        return withContext(Dispatchers.IO) {
            try {
                plantDao.getPlantItems()
            } catch (e: Exception) {
                Log.e(TAG, "getPlantItems error: ${e.message}")
                listOf()
            }
        }
    }

    override suspend fun getAnimalItems(): List<AnimalItem> {
        return withContext(Dispatchers.IO) {
            try {
                animalDao.getAnimalItems()
            } catch (e: Exception) {
                Log.d(TAG, "getAnimalItems error: ${e.message}")
                listOf()
            }
        }
    }

    override suspend fun getPlantItemsByArea(areaName: String): List<PlantItem> {
        return withContext(Dispatchers.IO) {
            try {
                plantDao.getPlantItemsByArea(areaName = areaName)
            } catch (e: Exception) {
                Log.e(TAG, "getPlantItemsByArea error: ${e.message}")
                listOf()
            }
        }
    }

    override suspend fun getAnimalItemsByArea(areaName: String): List<AnimalItem> {
        return withContext(Dispatchers.IO) {
            try {
                val areaNames = splitByNonAlphaCharacters(areaName)
                Log.d(TAG, "areaNames: $areaNames")
                val animalItems = mutableListOf<AnimalItem>()
                areaNames.map { areaName ->
                    animalItems += animalDao.getAnimalItemsByArea(areaName)
                }

                // 過濾掉重複的 AnimalItem (假設根據 id 屬性來確定唯一性)
                val distinctAnimalItems = animalItems.distinctBy { it.id }

                Log.d(TAG, "distinctAnimalItems: $distinctAnimalItems")
                distinctAnimalItems
            } catch (e: Exception) {
                Log.e(TAG, "getAnimalItemsByArea error: ${e.message}")
                listOf()
            }
        }
    }

    /** 將非字母字串拆分開 */
    fun splitByNonAlphaCharacters(input: String): List<String> {
        // 使用正則表達式拆分字串，分隔符是所有非字母字符和非中文字符
        val regex = "[^\\p{IsHan}a-zA-Z]+" // 匹配所有非中文字符和非字母字符
        return input.split(regex.toRegex()).filter { it.isNotEmpty() }
    }

}