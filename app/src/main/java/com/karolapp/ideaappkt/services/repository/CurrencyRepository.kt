package com.karolapp.ideaappkt.services.repository

import android.util.Log
import com.karolapp.ideaappkt.model.*
import com.karolapp.ideaappkt.services.api.ApiService
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class CurrencyRepository(private val apiService: ApiService) {
    fun getDetails(): Single<ItemHome> {
        return Single.zip(
            apiService.getCryptocurrency(),
            apiService.getIcons(),
            BiFunction<Rates, ListIconsCurrency, ItemHome>
            { rates, iconsCurrency ->
                createHomeItemModel(rates, iconsCurrency)
            })
    }

    private fun createHomeItemModel(cryptocurrency: Rates, iconsCurrency: ListIconsCurrency): ItemHome {
        Log.i("items",cryptocurrency.toString())
        Log.i("items2",iconsCurrency.items.toString())
        return ItemHome(cryptocurrency,iconsCurrency)
    }
}