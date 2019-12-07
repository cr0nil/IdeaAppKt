package com.karolapp.ideaappkt.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class IconsCurrency(
    @SerializedName("exchange_id")
    @Expose
    val exchange_id: String,
    @SerializedName("url")
    @Expose
    val url: String
)