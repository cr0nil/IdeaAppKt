package com.karolapp.ideaappkt.ui.presenter

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.crashlytics.android.Crashlytics
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Component.DaggerHomeFragmentComponent
import com.karolapp.ideaappkt.di.Module.ContextModule
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.services.WorkManager.WorkerManager
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.repository.CurrencyRepository
import com.karolapp.ideaappkt.ui.contract.HomeFragmentContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

class HomeFragmentPresenter : HomeFragmentContract.Presenter {
    private lateinit var view: HomeFragmentContract.View
    private val subscriptions = CompositeDisposable()

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)

    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: HomeFragmentContract.View) {
        this.view = view
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
                if (e is HttpException) {
                    view.showErrorMessage("Limit -- Try again later")
                    Log.e("error2", e.toString())

                } else {
                    Crashlytics.logException(e)
                }

            }

            override fun onComplete() {
                println("onComplete")
            }
        }
    }


}


