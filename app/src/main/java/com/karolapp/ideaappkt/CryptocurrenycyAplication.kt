package com.karolapp.ideaappkt

import android.app.Application
import com.karolapp.ideaappkt.di.Component.CryptocurrencyComponent
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.ContextModule
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule


open class CryptocurrenycyAplication : Application() {

    companion object {
        @JvmStatic
        lateinit var cryptocurrencyApplicationComponent: CryptocurrencyComponent
    }

    override fun onCreate() {
        super.onCreate()
        cryptocurrencyApplicationComponent = DaggerCryptocurrencyComponent
            .builder()
            .cryptocurrencyModule(CryptocurrencyModule())
            .contextModule(ContextModule(this))
            .homeFragmentModule(HomeFragmentModule())
            .build()
        cryptocurrencyApplicationComponent.inject(this)


    }


}