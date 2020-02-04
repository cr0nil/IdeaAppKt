package com.karolapp.ideaappkt.ui.presenter

import android.util.Log
import com.crashlytics.android.Crashlytics
import com.karolapp.ideaappkt.BaseApplication
import com.karolapp.ideaappkt.di.Component.DaggerAppComponent
import com.karolapp.ideaappkt.di.Module.AppModule
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.api.ApiClient
import com.karolapp.ideaappkt.services.api.ApiService
import com.karolapp.ideaappkt.services.repository.CurrencyRepository
import com.karolapp.ideaappkt.ui.contract.HomeFragmentContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class HomeFragmentPresenter @Inject constructor(private val service: ApiClient) : HomeFragmentContract.Presenter {
    private lateinit var view: HomeFragmentContract.View
    private val subscriptions = CompositeDisposable()

//    init {
//        BaseApplication.appApplicationComponent.inject(this)
//
//    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

//    override fun attach(view: HomeFragmentContract.View) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun attach(view: HomeFragmentContract.View) {
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
//        val service =
//            DaggerAppComponent.builder().appModule(AppModule())
//                .build()

        val currencyRepository = CurrencyRepository(service.create())
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


