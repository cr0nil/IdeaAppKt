package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.BaseApplication
import com.karolapp.ideaappkt.di.Module.AppModule
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.ui.presenter.DetailPreseter
import com.karolapp.ideaappkt.ui.presenter.HomeFragmentPresenter
import com.karolapp.ideaappkt.ui.view.DetailsFragment
import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Component


@Component(modules = arrayOf(AppModule::class, HomeFragmentModule::class))
interface AppComponent {
    fun gerCryptoService(): ApiService

    fun inject(app: BaseApplication)

    fun inject(homeFragmentPresenter: HomeFragmentPresenter)

    fun inject(homeFragment: HomeFragment)

    fun inject(detailsFragment: DetailsFragment)

    fun inject(detailPreseter: DetailPreseter)
}