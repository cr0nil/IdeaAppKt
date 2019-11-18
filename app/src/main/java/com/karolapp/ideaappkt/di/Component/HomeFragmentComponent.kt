package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Scope.HomeFragmentScope
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import dagger.Component


@Component(modules = arrayOf(HomeFragmentModule::class))
@HomeFragmentScope
interface HomeFragmentComponent {

    val recyclerViewAdapter: RecyclerViewAdapter
//    val randomUserAdapter: RandomUserAdapter
//
//    val randomUserService: RandomUsersApi

}