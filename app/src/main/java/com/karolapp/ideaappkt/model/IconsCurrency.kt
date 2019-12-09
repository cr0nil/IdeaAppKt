package com.karolapp.ideaappkt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class IconsCurrency(
    @SerializedName("asset_id")
    @Expose
    val asset_id: String,
    @SerializedName("url")
    @Expose
    val url: String
)