package com.example.quiz1.model

import com.google.gson.annotations.SerializedName

data class CampusModel(
    val name: String,
    val country: String,
    val stateProvince: String?,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("web_pages")
    val webPages: List<String>,
    val domains: List<String>
)