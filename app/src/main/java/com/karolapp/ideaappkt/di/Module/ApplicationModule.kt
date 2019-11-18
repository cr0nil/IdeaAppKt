package com.karolapp.ideaappkt.di.Module

import android.app.Application
import com.karolapp.ideaappkt.BaseApp
import com.karolapp.ideaappkt.di.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }

}