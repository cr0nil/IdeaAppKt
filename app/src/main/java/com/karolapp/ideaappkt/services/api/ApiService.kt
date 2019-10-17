package com.karolapp.ideaappkt.services.api

import com.karolapp.ideaappkt.model.Rates
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {

//    var apiKey = "E23C7AC4-72E4-4CCD-9FE5-B5BDCD99449B"
    @Headers(

        "X-CoinAPI-Key:E23C7AC4-72E4-4CCD-9FE5-B5BDCD99449B"
    )
    @GET("exchangerate/USD/")
    fun getCryptocurrency(): Observable<Rates>
}