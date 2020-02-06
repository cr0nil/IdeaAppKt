package com.karolapp.ideaappkt.di.Module

import com.karolapp.ideaappkt.ui.contract.AlarmContract
import com.karolapp.ideaappkt.ui.contract.DetailContract
import com.karolapp.ideaappkt.ui.contract.HomeFragmentContract
import com.karolapp.ideaappkt.ui.presenter.AlarmPresenter
import com.karolapp.ideaappkt.ui.presenter.DetailPreseter
import com.karolapp.ideaappkt.ui.presenter.HomeFragmentPresenter
import dagger.Module
import dagger.Provides

@Module
public class HomeFragmentModule {

    @Provides
    fun provideLRecyclerContract(): HomeFragmentContract.Presenter = HomeFragmentPresenter()

    @Provides
    fun provideDetailContract(): DetailContract.Presenter = DetailPreseter()
    @Provides
    fun provideAlarmContract(): AlarmContract.Presenter = AlarmPresenter()

}