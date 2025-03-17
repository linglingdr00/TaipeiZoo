package com.linglingdr00.taipeizoo.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linglingdr00.taipeizoo.model.data.item.PlantItem

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlantItems(plantItems: List<PlantItem>)

    @Query("SELECT * FROM table_plant")
    fun getPlantItems(): List<PlantItem>

    /** 可利用 "%areaName%" 做模糊查詢 */
    @Query("SELECT * FROM table_plant WHERE FLocation LIKE '%' || :areaName || '%' OR FLocation LIKE '%' || :fixedText || '%'")
    fun getPlantItemsByArea(areaName: String, fixedText: String = "全園普遍分佈", ): List<PlantItem>

}