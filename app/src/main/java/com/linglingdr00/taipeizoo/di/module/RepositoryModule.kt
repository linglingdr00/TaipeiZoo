package com.linglingdr00.taipeizoo.di.module

import com.linglingdr00.taipeizoo.model.data.dao.AnimalDao
import com.linglingdr00.taipeizoo.model.data.dao.AreaDao
import com.linglingdr00.taipeizoo.model.data.dao.PlantDao
import com.linglingdr00.taipeizoo.model.network.ZooApiService
import com.linglingdr00.taipeizoo.model.repository.MainLocalRepository
import com.linglingdr00.taipeizoo.model.repository.MainLocalRepositoryImpl
import com.linglingdr00.taipeizoo.model.repository.MainRemoteRepository
import com.linglingdr00.taipeizoo.model.repository.MainRemoteRepositoryImpl
import com.linglingdr00.taipeizoo.model.repository.MainRepository
import com.linglingdr00.taipeizoo.model.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainLocalRepository(
        areaDao: AreaDao,
        plantDao: PlantDao,
        animalDao: AnimalDao
    ): MainLocalRepository {
        return MainLocalRepositoryImpl(areaDao, plantDao, animalDao)
    }

    @Provides
    @Singleton
    fun provideMainRemoteRepository(
        zooApiService: ZooApiService
    ): MainRemoteRepository {
        return MainRemoteRepositoryImpl(zooApiService)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        localRepository: MainLocalRepository,
        remoteRepository: MainRemoteRepository
    ): MainRepository {
        return MainRepositoryImpl(localRepository, remoteRepository)
    }

}