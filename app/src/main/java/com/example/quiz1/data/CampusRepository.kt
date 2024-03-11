package com.example.quiz1.data

import com.example.quiz1.model.CampusModel
import com.example.quiz1.network.CampusApiService
import retrofit2.Call

interface CampusRepository {
    suspend fun getCampus(): Call<List<CampusModel>>
}

class NetworkRepository(private val campusApiService: CampusApiService) : CampusRepository {
    override suspend fun getCampus(): Call<List<CampusModel>> =
        campusApiService.getCampus(country = "indonesia")
}