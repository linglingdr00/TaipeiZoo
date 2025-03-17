package com.linglingdr00.taipeizoo.model.network

import com.linglingdr00.taipeizoo.model.data.response.AnimalResponse
import com.linglingdr00.taipeizoo.model.data.response.AreaResponse
import com.linglingdr00.taipeizoo.model.data.response.PlantResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ZooApiService {

    // https://data.taipei/api/v1/dataset/${resource_id}?scope=resourceAquire
    @GET("api/v1/dataset/{resource_id}")
    suspend fun getAreaData(
        @Path("resource_id") resourceId: String = "9683ba26-109e-4cb8-8f3d-03d1b349db9f",
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") keyword: String = "",
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0

    ): AreaResponse

    // https://data.taipei/api/v1/dataset/${{resource_id}}?scope=resourceAquire
    @GET("api/v1/dataset/{resource_id}")
    suspend fun getPlantData(
        @Path("resource_id") resourceId: String = "e20706d8-bf89-4e6a-9768-db2a10bb2ba4",
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") keyword: String = "",
        @Query("limit") limit: Int = 200,
        @Query("offset") offset: Int = 0
    ): PlantResponse

    // https://data.taipei/api/v1/dataset/${resource_id}?scope=resourceAquire
    @GET("api/v1/dataset/{resource_id}")
    suspend fun getAnimalData(
        @Path("resource_id") resourceId: String = "6afa114d-38a2-4e3c-9cfd-29d3bd26b65b",
        @Query("scope") scope: String = "resourceAquire",
        @Query("q") keyword: String = "",
        @Query("limit") limit: Int = 300,
        @Query("offset") offset: Int = 0

    ): AnimalResponse

}