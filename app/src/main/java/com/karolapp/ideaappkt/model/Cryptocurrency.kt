package com.karolapp.ideaappkt.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose


class Cryptocurrency {
    @SerializedName("asset_id_quote")
    @Expose
    internal var name: String? = null
    @SerializedName("time")
    @Expose
    private var time: String? = null

    @Expose
    @SerializedName("rate")
    private var rate: String? = null

    constructor(name: String?, time: String?, rate: String?) {
        this.name = name
        this.time = time
        this.rate = rate
    }

    fun getRate(): String? {
        return rate
    }

    fun setRate(rate: String) {
        this.rate = rate
    }

    fun getTime(): String? {
        return time
    }

    fun setTime(time: String) {
        this.time = time
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }


}