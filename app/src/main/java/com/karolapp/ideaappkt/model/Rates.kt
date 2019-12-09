package com.karolapp.ideaappkt.model

import com.google.gson.annotations.SerializedName


data class Rates(
    @SerializedName("asset_id_base")
     var asset_id_base: String? = null,

    @SerializedName("rates")
     var cryptocurrencyList: List<Cryptocurrency>? = null
)