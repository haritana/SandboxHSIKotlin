package com.example.quiz1.data

import com.example.quiz1.network.CampusApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val campusRepository: CampusRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "http://universities.hipolabs.com/"
    private val retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val retrofitService : CampusApiService by lazy {
        retrofit.create(CampusApiService::class.java)
    }

    override val campusRepository: CampusRepository by lazy {
        NetworkRepository(retrofitService)
    }
}