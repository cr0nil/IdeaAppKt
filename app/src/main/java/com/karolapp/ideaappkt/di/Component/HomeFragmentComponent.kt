package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Scope.PerFragment
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import dagger.Component


@Component(
    modules = arrayOf(HomeFragmentModule::class),
    dependencies = arrayOf(CryptocurrencyComponent::class)
)
@PerFragment
interface HomeFragmentComponent {

    val recyclerViewAdapter: RecyclerViewAdapter
    //val getCrytptoCurrencyService: ApiService
    fun inject(listFragment: CryptocurrenycyAplication)
//    val randomUserAdapter: RandomUserAdapter
//
//    val randomUserService: RandomUsersApi

}