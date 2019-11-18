package com.karolapp.ideaappkt.di.Module

import android.content.Context
import com.karolapp.ideaappkt.di.Scope.ApliactionScop
import com.karolapp.ideaappkt.di.Scope.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class ContextModule(internal var context: Context) {

    @ApplicationContext
    @ApliactionScop
    @Provides
    fun context(): Context {
        return context.getApplicationContext()
    }
}