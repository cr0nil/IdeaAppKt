package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.ui.presenter.DetailPreseter
import com.karolapp.ideaappkt.ui.presenter.HomeFragmentPresenter
import com.karolapp.ideaappkt.ui.view.DetailsFragment
import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Component


@Component(modules = arrayOf(CryptocurrencyModule::class, HomeFragmentModule::class))
interface CryptocurrencyComponent {
    fun gerCryptoService(): ApiService

    fun inject(app: CryptocurrenycyAplication)

    fun inject(homeFragmentPresenter: HomeFragmentPresenter)

    fun inject(homeFragment: HomeFragment)

    fun inject(detailsFragment: DetailsFragment)

    fun inject(detailPreseter: DetailPreseter)
}