package com.karolapp.ideaappkt.di.Module

import androidx.fragment.app.Fragment
import com.karolapp.ideaappkt.ui.contract.DetailContract
import com.karolapp.ideaappkt.ui.contract.HomeFragmentContract
import com.karolapp.ideaappkt.ui.presenter.DetailPreseter
import com.karolapp.ideaappkt.ui.presenter.HomeFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
public class HomeFragmentModule (){

    @Provides
    fun provideLRecyclerContract(): HomeFragmentContract.Presenter = HomeFragmentPresenter()

    @Provides
    fun provideDetailContract(): DetailContract.Presenter = DetailPreseter()
}