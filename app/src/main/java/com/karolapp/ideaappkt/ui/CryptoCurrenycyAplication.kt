package com.karolapp.ideaappkt.ui

import android.app.Application
import android.app.Activity

import com.karolapp.ideaappkt.di.FragmentComponent
import com.karolapp.ideaappkt.di.Module.ContextModule


class CryptoCurrenycyAplication : Application(){
    //add application name in Manifest file
    private var randomUserApplicationComponent: FragmentComponent? = null

    operator fun get(activity: Activity): FragmentComponent {
        return activity.application as FragmentComponent
    }

    override fun onCreate() {
        super.onCreate()


//        randomUserApplicationComponent = DaggerRandomUserComponent.builder()
//            .contextModule(ContextModule(this))
//            .build()
    }
//
//    fun getRandomUserApplicationComponent(): RandomUserComponent? {
//        return randomUserApplicationComponent
//    }
}