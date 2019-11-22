package com.karolapp.ideaappkt.di.Module

import com.karolapp.ideaappkt.di.Scope.PerFragment
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import com.karolapp.ideaappkt.ui.presenter.RecycelerViewPresenter
import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Module
import dagger.Provides

@Module
public class HomeFragmentModule {

    @Provides
    fun provideLRecyclerContract(): RecyclerContract.Presenter = RecycelerViewPresenter()
}