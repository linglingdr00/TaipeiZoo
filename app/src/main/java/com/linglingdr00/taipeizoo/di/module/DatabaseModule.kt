package com.linglingdr00.taipeizoo.di.module

import android.content.Context
import com.linglingdr00.taipeizoo.model.data.dao.AnimalDao
import com.linglingdr00.taipeizoo.model.data.dao.AreaDao
import com.linglingdr00.taipeizoo.model.data.dao.PlantDao
import com.linglingdr00.taipeizoo.model.database.ZooDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ZooDatabase {
        return ZooDatabase.Companion.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAreaDao(database: ZooDatabase): AreaDao {
        return database.areaDao()
    }

    @Provides
    @Singleton
    fun providePlantDao(database: ZooDatabase): PlantDao {
        return database.plantDao()
    }

    @Provides
    @Singleton
    fun provideAnimalDao(database: ZooDatabase): AnimalDao {
        return database.animalDao()
    }

}