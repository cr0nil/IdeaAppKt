package com.karolapp.ideaappkt.services.api

import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.Rates
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


public interface ApiService {

    @Headers(

        "X-CoinAPI-Key:E23C7AC4-72E4-4CCD-9FE5-B5BDCD99449B"
    )
    @GET("exchangerate/USD/")
    fun getCryptocurrency(): Observable<Rates>
    @Headers(

        "X-CoinAPI-Key:E23C7AC4-72E4-4CCD-9FE5-B5BDCD99449B"
    )
    @GET("ohlcv/{asset_id_base}/USD/history?period_id=10DAY&time_start=2017-01-01T00:00:00&limit=80")
    fun getHistoricalData(@Path("asset_id_base") baseId: String): Observable<List<HistoricalData>>
    @Headers(

        "X-CoinAPI-Key:E23C7AC4-72E4-4CCD-9FE5-B5BDCD99449B"
    )
    @GET("exchanges/icons/100x100")
    fun getIcons(): Observable<List<IconsCurrency>>
}