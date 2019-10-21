package com.karolapp.ideaappkt.di

import com.karolapp.ideaappkt.ui.view.HomeFragment
import dagger.Component

@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

//    fun inject(aboutFragment: AboutFragment)

    fun inject(listFragment: HomeFragment)

}