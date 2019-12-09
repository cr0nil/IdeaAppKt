package com.karolapp.ideaappkt.services.repository

import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.api.ApiService
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class CurrencyRepository(private val apiService: ApiService) {
    fun getRatesAndIcons(): Observable<ItemHome> {

        return Observable.zip(
            apiService.getCryptocurrency(),
            apiService.getIcons(),
            BiFunction<Rates, List<IconsCurrency>, ItemHome>
            { rates, iconsCurrency ->
                ItemHome(rates,iconsCurrency)
            })
    }

}