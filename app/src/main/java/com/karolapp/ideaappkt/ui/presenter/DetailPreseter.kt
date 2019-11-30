package com.karolapp.ideaappkt.ui.presenter

import android.util.Log
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.ui.contract.DetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPreseter : DetailContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DetailContract.View
    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)

    }
    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun getHistoricalData(base_id: String) {
        val service =
            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
                .build()


        var subscribe = service.gerCryptoService().getHistoricalData("BTC")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({it->
                view.loadDataSuccess(it)


            })

//
//        subscriptions.add(subscribe)
    }

}