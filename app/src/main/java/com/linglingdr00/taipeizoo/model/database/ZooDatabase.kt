package com.linglingdr00.taipeizoo.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linglingdr00.taipeizoo.model.data.dao.AnimalDao
import com.linglingdr00.taipeizoo.model.data.dao.AreaDao
import com.linglingdr00.taipeizoo.model.data.dao.PlantDao
import com.linglingdr00.taipeizoo.model.data.item.AnimalItem
import com.linglingdr00.taipeizoo.model.data.item.AreaItem
import com.linglingdr00.taipeizoo.model.data.item.PlantItem

@Database(entities = [AreaItem::class, PlantItem::class, AnimalItem::class], version = 1)
abstract class ZooDatabase: RoomDatabase() {
    // dao
    abstract fun areaDao(): AreaDao
    abstract fun plantDao(): PlantDao
    abstract fun animalDao(): AnimalDao

    companion object {
        // table name
        const val TABLE_AREA = "table_area"
        const val TABLE_PLANT = "table_plant"
        const val TABLE_ANIMAL = "table_animal"

        @Volatile
        private var INSTANCE: ZooDatabase? = null

        fun getDatabase(context: Context): ZooDatabase {
            // synchronized 區塊中，一次只能執行一個 thread，可避免多個 thread 並發執行
            return INSTANCE ?: synchronized(this) {
                // build database instance
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZooDatabase::class.java,
                    "zoo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}