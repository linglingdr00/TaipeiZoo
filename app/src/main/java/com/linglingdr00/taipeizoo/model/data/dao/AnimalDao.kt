package com.linglingdr00.taipeizoo.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAnimalItems(animalItems: List<AnimalItem>)

    @Query("SELECT * FROM table_animal")
    fun getAnimalItems(): List<AnimalItem>

    /** 可利用 "%areaName%" 做模糊查詢 */
    @Query("SELECT * FROM table_animal WHERE aLocation LIKE '%' || :areaName || '%'")
    fun getAnimalItemsByArea(areaName: String): List<AnimalItem>

}