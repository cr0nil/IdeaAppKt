package com.karolapp.ideaappkt.ui.presenter

import android.util.Log
import com.crashlytics.android.Crashlytics
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.repository.CurrencyRepository
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class RecycelerViewPresenter : RecyclerContract.Presenter {
    private lateinit var view: RecyclerContract.View
    private val subscriptions = CompositeDisposable()

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)

    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: RecyclerContract.View) {
        this.view = view
    }


    override fun loadData(adapter: RecyclerViewAdapter) {
//        val service =
//            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
//                .build()
//
//
//        var subscribe = service.gerCryptoService().getCryptocurrency()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ rates: Rates ->
//                view.loadDataSuccess(rates, adapter)
//            })
//
//
//        subscriptions.add(subscribe)
    }


    override fun getRepositoryCurrency(adapter: RecyclerViewAdapter) {
//        view?.showLoading()
        val service =
            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
                .build()
        val currencyRepository = CurrencyRepository(service.gerCryptoService())
        currencyRepository.getRatesAndIcons()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                getObserver(adapter)
            )
    }

    private fun getObserver(adapter: RecyclerViewAdapter): Observer<ItemHome> {
        return object : Observer<ItemHome> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onNext(userList: ItemHome) {
                Log.i("on next", userList.iconsCurrency.toString())
                view.loadDataSuccess(userList.cryptocurrency, userList.iconsCurrency, adapter)

            }

            override fun onError(e: Throwable) {
                if(e is HttpException){
                    view.showErrorMessage("Limit -- Try again later")
                    Log.e("error2",e.toString())

                }else{
                    Crashlytics.logException(e)
                }

            }

            override fun onComplete() {
                println("onComplete")
            }
        }
    }
}


