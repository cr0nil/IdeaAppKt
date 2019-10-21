package com.karolapp.ideaappkt.di

import com.karolapp.ideaappkt.services.api.ApiClient
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import com.karolapp.ideaappkt.ui.presenter.RecycelerViewPresenter
import dagger.Module
import dagger.Provides
@Module
class FragmentModule {
    @Provides
    fun provideRecyclerViewPresenter(): RecyclerContract.Presenter {
        return RecycelerViewPresenter()
    }
    @Provides
    fun provideApiService(): ApiService {
        return ApiClient.create()
    }
}