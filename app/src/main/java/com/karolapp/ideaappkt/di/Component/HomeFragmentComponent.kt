package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Scope.HomeFragmentScope
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.api.ApiService
import dagger.Component


@Component(
    modules = arrayOf(HomeFragmentModule::class),
    dependencies = arrayOf(CryptocurrencyComponent::class)
)
@HomeFragmentScope
interface HomeFragmentComponent {

    val recyclerViewAdapter: RecyclerViewAdapter
    fun getCrytptoCurrencyServis(): ApiService
//    val randomUserAdapter: RandomUserAdapter
//
//    val randomUserService: RandomUsersApi

}