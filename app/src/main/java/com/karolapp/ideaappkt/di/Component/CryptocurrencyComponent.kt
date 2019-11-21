package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.di.Scope.PerApplication
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import dagger.Component

@PerApplication
@Component(modules = arrayOf(CryptocurrencyModule::class))
interface CryptocurrencyComponent {
    //val getCrytptoCurrencyService : ApiService

    fun inject(app: CryptocurrenycyAplication)
}