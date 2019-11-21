package com.karolapp.ideaappkt.di.Module

import com.karolapp.ideaappkt.di.Scope.PerFragment
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Module
import dagger.Provides

@Module
public class HomeFragmentModule {

    lateinit var  homeFragment: HomeFragment

    constructor(homeFragment: HomeFragment) {
        this.homeFragment = homeFragment
    }


    @Provides
    @PerFragment
    public fun recyclerViewAdapter(): RecyclerViewAdapter {
        return  RecyclerViewAdapter(fragment = homeFragment)
    }
}