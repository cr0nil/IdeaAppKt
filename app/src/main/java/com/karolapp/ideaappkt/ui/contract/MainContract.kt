package com.karolapp.ideaappkt.ui.contract

class MainContract {

    interface View : BaseContract.View {
        fun showAboutFragment()
        fun showListFragment()
    }
}