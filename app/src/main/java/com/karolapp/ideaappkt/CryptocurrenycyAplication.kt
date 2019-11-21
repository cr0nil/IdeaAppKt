package com.karolapp.ideaappkt

import android.app.Application
import androidx.fragment.app.Fragment
import com.karolapp.ideaappkt.di.Component.CryptocurrencyComponent
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent

import com.karolapp.ideaappkt.di.Module.ContextModule
import com.karolapp.ideaappkt.ui.view.HomeFragment


open class CryptocurrenycyAplication : Application() {
    //
    lateinit var  cryptocurrencyApplicationComponent: CryptocurrencyComponent

    companion object {
        lateinit var instance: CryptocurrenycyAplication private set

        fun get(fragment: Fragment): HomeFragment {
            return fragment.activity!!.application as HomeFragment
        }
    }

    override fun onCreate() {
        super.onCreate()
        cryptocurrencyApplicationComponent = DaggerCryptocurrencyComponent.builder().contextModule(ContextModule(this)).build()
        cryptocurrencyApplicationComponent.inject(this)


    }
    fun getApplicationComponent(): CryptocurrencyComponent {
        return cryptocurrencyApplicationComponent
    }
////
//    fun getCryptocurrencyApplicationComponent(): CryptocurrencyComponent? {
//        return cryptocurrencyApplicationComponent
//    }

}