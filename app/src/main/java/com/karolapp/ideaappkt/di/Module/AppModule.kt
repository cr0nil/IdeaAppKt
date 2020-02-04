package com.karolapp.ideaappkt.di.Module

import android.app.Application
import com.karolapp.ideaappkt.BaseApplication
import com.karolapp.ideaappkt.di.Scope.PerApplication
import com.karolapp.ideaappkt.services.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module(includes = arrayOf(ContextModule::class))
public class AppModule(private val baseApp:BaseApplication) {

    @Provides
    fun provideApplication():Application{
        return baseApp
    }

    @Provides
    fun randomUsersApi(retrofit: Retrofit): ApiService {
        return retrofit.create()
    }


    @Provides
    fun create(): Retrofit {

        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rest.coinapi.io/v1/")
            .build()


    }
}