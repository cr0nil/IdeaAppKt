package com.karolapp.ideaappkt.di.Module

import com.karolapp.ideaappkt.di.Scope.ApliactionScop
import com.karolapp.ideaappkt.services.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
public class CryptoCurrencyModule {
    @ApliactionScop
    @Provides
    fun create(): Retrofit {

        return  Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rest.coinapi.io/v1/")
            .build()


    }
}