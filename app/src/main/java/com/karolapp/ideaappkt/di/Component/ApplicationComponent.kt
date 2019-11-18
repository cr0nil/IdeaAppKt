package com.karolapp.ideaappkt.di.Component

import com.karolapp.ideaappkt.BaseApp
import com.karolapp.ideaappkt.di.Module.ApplicationModule
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}