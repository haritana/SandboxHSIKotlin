package com.example.quiz1.network

import com.example.quiz1.model.CampusModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CampusApiService {
    @GET("search")
    fun getCampus(@Query("country") country: String): Call<List<CampusModel>>
}