package com.karolapp.ideaappkt.ui.presenter

import android.content.Context
import android.util.Log
import androidx.navigation.NavController
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.repository.CurrencyRepository
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File.separator

class RecycelerViewPresenter : RecyclerContract.Presenter {
    private lateinit var view: RecyclerContract.View
    private val subscriptions = CompositeDisposable()
    private var navController: NavController? = null
    //    @Inject
//    lateinit var service: ApiService

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
        val service =
            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
                .build()


        var subscribe = service.gerCryptoService().getCryptocurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ rates: Rates ->
                view.loadDataSuccess(rates, adapter)
            })


        subscriptions.add(subscribe)
    }

    override fun getDetailsCurrency(id: String) {

    }

    override fun getIcons(context: Context) {
        val service =
            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
                .build()

//
//        var subscribe = service.gerCryptoService().getIcons()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                view.loadIconSuccess(it)
//            })
//
//
//        subscriptions.add(subscribe)
//        Glide
//            .with(context)
//            .load()
//            .downloadOnly(2000, 2000)
//    emitter.onComplete();    }
    }

//    override fun getDetailsMovie(id: String, view : View) {
//
//    }


    override fun getDetails() {
//        view?.showLoading()
        val service =
            DaggerCryptocurrencyComponent.builder().cryptocurrencyModule(CryptocurrencyModule())
                .build()
        val currencyRepository = CurrencyRepository(service.gerCryptoService())
        currencyRepository.getDetails()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                Log.i("presenter", it.iconsCurrency.get(0).url)
            },
                { error ->
                    Log.e("jakiś error", error.toString())
                })
    }
    private fun getObserver(): Observer<List<IconsCurrency>> {
        return object : Observer<List<IconsCurrency>> {
            override fun onSubscribe(d: Disposable) {
                println("onSubscribe")
            }

            override fun onNext(userList: List<IconsCurrency>) {
                println("onNext : $userList")
            }

            override fun onError(e: Throwable) {
                println("onError : ${e.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }
    }
}


