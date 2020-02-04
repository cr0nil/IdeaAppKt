package com.karolapp.ideaappkt

import android.app.Application
import com.karolapp.ideaappkt.di.Component.AppComponent
import com.karolapp.ideaappkt.di.Component.DaggerAppComponent
import com.karolapp.ideaappkt.di.Module.ContextModule
import com.karolapp.ideaappkt.di.Module.AppModule
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule


open class BaseApplication : Application() {
    lateinit var appApplicationComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appApplicationComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .contextModule(ContextModule(this))
            .homeFragmentModule(HomeFragmentModule())
            .build()
        appApplicationComponent.inject(this)


    }
    fun getAppComponent():AppComponent{
        return appApplicationComponent
    }
    companion object {
        @JvmStatic
        lateinit var instace: AppComponent
    }


}