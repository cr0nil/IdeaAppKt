package com.karolapp.ideaappkt.di.Module

import com.karolapp.ideaappkt.services.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
public class CryptoCurrencyModul {
    @Provides
    fun create(): ApiService {

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rest.coinapi.io/v1/")
            .build()

        return retrofit.create(ApiService::class.java)
    }
}