package com.linglingdr00.taipeizoo.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linglingdr00.taipeizoo.model.data.item.AreaItem

@Dao
interface AreaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAreaItems(areaItems: List<AreaItem>)

    @Query("SELECT * FROM table_area")
    fun getAreaItems(): List<AreaItem>

}