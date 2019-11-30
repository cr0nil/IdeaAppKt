package com.karolapp.ideaappkt.model


data class HistoricalData(
    val time_period_start: String,
    val time_period_end: String,
    val time_open: String,
    val time_close: String,
    val price_open: Double,
    val price_high: Double,
    val price_low: Double,
    val price_close: Double,
    val volume_traded: Double,
    val trades_count: Int
)