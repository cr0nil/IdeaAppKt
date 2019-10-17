package com.karolapp.ideaappkt.model

import com.google.gson.annotations.SerializedName


class Rates {
    @SerializedName("asset_id_base")
    private var asset_id_base: String? = null

    @SerializedName("rates")
    private var cryptocurrencyList: ArrayList<Cryptocurrency>? = null

    constructor(asset_id_base: String?, cryptocurrencyList: ArrayList<Cryptocurrency>?) {
        this.asset_id_base = asset_id_base
        this.cryptocurrencyList = cryptocurrencyList
    }


    fun getCryptocurrencyList(): ArrayList<Cryptocurrency>? {
        return cryptocurrencyList
    }

    fun setCryptocurrencyList(cryptocurrencyList: ArrayList<Cryptocurrency>) {
        this.cryptocurrencyList = cryptocurrencyList
    }

    fun getAsset_id_base(): String? {
        return asset_id_base
    }

    fun setAsset_id_base(asset_id_base: String) {
        this.asset_id_base = asset_id_base
    }


}