package com.karolapp.ideaappkt.di.Module

import android.content.Context
import com.karolapp.ideaappkt.di.Scope.PerApplication
import com.karolapp.ideaappkt.di.Scope.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class ContextModule(internal var context: Context) {

    @ApplicationContext
    @PerApplication
    @Provides
    fun context(): Context {
        return context.getApplicationContext()
    }
}