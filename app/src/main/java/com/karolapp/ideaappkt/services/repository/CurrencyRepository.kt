package com.karolapp.ideaappkt.services.repository

import android.util.Log
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.model.ListIconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.api.ApiService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class CurrencyRepository(private val apiService: ApiService) {
    fun getDetails(): Observable<ItemHome> {

        return Observable.zip(
            apiService.getCryptocurrency(),
            apiService.getIcons(),
            BiFunction<Rates, List<IconsCurrency>, ItemHome>
            { rates, iconsCurrency ->
//                Log.i("rates", rates.toString())
                Log.i("rates", iconsCurrency.size.toString())
                ItemHome(rates,iconsCurrency)
//

               // createHomeItemModel(rates, iconsCurrency)
            })
    }

}