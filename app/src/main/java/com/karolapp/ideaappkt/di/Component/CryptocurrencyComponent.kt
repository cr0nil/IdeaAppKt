package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Module.CryptoCurrencyModule
import com.karolapp.ideaappkt.di.Scope.ApliactionScop
import com.karolapp.ideaappkt.services.api.ApiService
import dagger.Component

@ApliactionScop
@Component(modules = arrayOf(CryptoCurrencyModule::class))
interface CryptocurrencyComponent {
    fun getCrytptoCurrencyServis(): ApiService
}