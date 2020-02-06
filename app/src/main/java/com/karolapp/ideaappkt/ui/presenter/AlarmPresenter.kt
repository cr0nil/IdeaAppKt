package com.karolapp.ideaappkt.ui.presenter

import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.ui.contract.AlarmContract

class AlarmPresenter : AlarmContract.Presenter {
    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)

    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attach(view: AlarmContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}