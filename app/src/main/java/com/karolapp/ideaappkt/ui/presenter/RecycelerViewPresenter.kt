package com.karolapp.ideaappkt.ui.presenter

import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.api.ApiClient
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RecycelerViewPresenter : RecyclerContract.Presenter {
//    private val service: ApiService = ApiClient.create()
    private lateinit var view: RecyclerContract.View
    private val subscriptions = CompositeDisposable()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: RecyclerContract.View) {
        this.view = view
    }


    override fun loadData(adapter : RecyclerViewAdapter,service: ApiService ) {
        var subscribe = service.getCryptocurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ rates: Rates ->
                view.loadDataSuccess(rates,adapter)
            })


        subscriptions.add(subscribe)
    }
}


