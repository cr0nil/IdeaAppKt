package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Component


@Component(
    modules = arrayOf(HomeFragmentModule::class),
    dependencies = arrayOf(CryptocurrencyComponent::class)
)

interface HomeFragmentComponent {

    fun inject(listFragment: HomeFragment)


}