package com.linglingdr00.taipeizoo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.model.data.item.PlantItem
import com.linglingdr00.taipeizoo.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    companion object {
        private const val TAG = "MainViewModel"
    }

    private val _areaItems = MutableLiveData<List<AreaItem>>()
    val areaItems: MutableLiveData<List<AreaItem>> = _areaItems

    private val _plantItems = MutableLiveData<List<PlantItem>>()
    val plantItems: MutableLiveData<List<PlantItem>> = _plantItems

    private val _animalItems = MutableLiveData<List<AnimalItem>>()
    val animalItems: MutableLiveData<List<AnimalItem>> = _animalItems

    private val _loadAreaDataStatus = MutableLiveData<Boolean>(false)
    val loadAreaDataStatus: MutableLiveData<Boolean> = _loadAreaDataStatus

    private val _loadPlantDataStatus = MutableLiveData<Boolean>(false)
    val loadPlantDataStatus: MutableLiveData<Boolean> = _loadPlantDataStatus

    private val _loadAnimalDataStatus = MutableLiveData<Boolean>(false)
    val loadAnimalDataStatus: MutableLiveData<Boolean> = _loadAnimalDataStatus

    private val _loadAllDataStatus = MutableLiveData<Boolean>(false)
    val loadAllDataStatus: MutableLiveData<Boolean> = _loadAllDataStatus

    private val _animalImageUrls = MutableLiveData<List<String>>()
    val animalImageUrls: MutableLiveData<List<String>> = _animalImageUrls

    private val _plantImageUrls = MutableLiveData<List<String>>()
    val plantImageUrls: MutableLiveData<List<String>> = _plantImageUrls

    /** 取得所有資料 */
    fun getAllData() {
        Log.d(TAG, "getAllData()")
        viewModelScope.launch {
            // 使用 async 啟動並行任務
            val areaDeferred = async { getAreaItemData() }
            val plantDeferred = async { getPlantData() }
            val animalDeferred = async { getAnimalData() }

            // 等待所有任務完成
            areaDeferred.await()
            plantDeferred.await()
            animalDeferred.await()

            // 所有任務都完成後更新狀態
            _loadAllDataStatus.postValue(true)
        }
    }

    /** 取得館區資料 */
    fun getAreaItemData() {
        viewModelScope.launch {
            try {
                val areaItems = mainRepository.getAreaItems()
                //Log.d(TAG, "areaItems: $areaItems")
                _areaItems.postValue(areaItems)
                _loadAreaDataStatus.postValue(true)
            } catch (e: Exception) {
                Log.d(TAG, "getAreaItems error: ${e.message}")
            }
        }
    }

    /** 取得植物資料 */
    fun getPlantData() {
        viewModelScope.launch {
            try {
                val plantItems = mainRepository.getPlantItems()
                //Log.d(TAG, "plantItems: $plantItems")
            } catch (e: Exception) {
                Log.d(TAG, "getPlantItems error: ${e.message}")
            }
        }
    }

    /** 取得動物資料 */
    fun getAnimalData() {
        viewModelScope.launch {
            try {
                val animalItems = mainRepository.getAnimalItems()
                //Log.d(TAG, "animalItems: $animalItems")
            } catch (e: Exception) {
                Log.d(TAG, "getAnimalItems error: ${e.message}")
            }
        }
    }

    /** 根據 area name 獲取植物資料 */
    fun getPlantItemsByArea(areaName: String) {
        viewModelScope.launch {
            try {
                val plantItems = mainRepository.getPlantItemsByArea(areaName)
                _plantItems.postValue(plantItems)
                _loadPlantDataStatus.postValue(true)
            } catch (e: Exception) {
                Log.d(TAG, "getPlantItemsByArea error: ${e.message}")
            }
        }
    }

    /** 根據 area name 獲取動物資料 */
    fun getAnimalItemsByArea(areaName: String) {
        viewModelScope.launch {
            try {
                val animalItems = mainRepository.getAnimalItemsByArea(areaName)
                _animalItems.postValue(animalItems)
                _loadAnimalDataStatus.postValue(true)
            } catch (e: Exception) {
                Log.d(TAG, "getAnimalItemsByArea error: ${e.message}")
            }
        }
    }

    /** 取得動物圖片 url list */
    fun getAnimalImageUrls(animalItem: AnimalItem) {
        // load image url list
        val imgUrlList = listOf(
            animalItem.aPic01Url, animalItem.aPic02Url, animalItem.aPic03Url, animalItem.aPic04Url
        ).filterNot { it.isEmpty() }
        _animalImageUrls.value = imgUrlList
    }

    /** 取得植物圖片 url list */
    fun getPlantImageUrls(plantItem: PlantItem) {
        // load image url list
        val imgUrlList = listOf(plantItem.fPic01Url).filterNot { it.isEmpty() }
        _plantImageUrls.value = imgUrlList
    }

    /** 取得動物基本資訊 */
    fun getAnimalInfo(animalItem: AnimalItem): String {
        // 英文名｜Giant Panda\n學名｜Ailuropoda melanoleuca\n分類｜脊索動物門＞哺乳綱＞食肉目＞熊科
        return animalItem.let {
            "英文名｜${it.aNameEn}\n" + "學名｜${it.aNameLatin}\n" + "分類｜${it.aPhylum}>${it.aClass}>${it.aOrder}>${it.aFamily}"
        }
    }

    /** 取得植物基本資訊 */
    fun getPlantInfo(plantItem: PlantItem): String {
        // 英文名｜Subcostate Crape Myrtle\n學名｜Lagerstroemia subcostata\n分類｜千屈菜科＞紫薇屬
        return plantItem.let {
            "英文名｜${it.fNameEn}\n" + "學名｜${it.fNameLatin}\n" +
                    "分類｜${extractChinese(it.fFamily)}>${extractChinese(it.fGenus)}"
        }
    }

    /** 只提取中文 */
    fun extractChinese(input: String): String {
        // 使用正則表達式只保留中文字符
        val regex = "[^\\p{IsHan}]+"
        return input.replace(regex.toRegex(), "")
    }

    /** 清空 animalItems */
    fun clearAnimalItems() {
        _animalItems.value = listOf()
    }

    /** 清空 plantItems */
    fun clearPlantItems() {
        _plantItems.value = listOf()
    }

}