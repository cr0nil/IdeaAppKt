package com.karolapp.ideaappkt.ui.presenter

import android.content.Context
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.di.Component.DaggerCryptocurrencyComponent
import com.karolapp.ideaappkt.di.Module.CryptocurrencyModule
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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
        Glide
            .with(context)
            .load()
            .downloadOnly(2000, 2000)
//    emitter.onComplete();    }

        fun getImg(context: Context) {
//    Glide
//        .with(context)
//        .load()
//        .downloadOnly(2000, 2000)
//    emitter.onComplete();
        }
//    override fun getDetailsMovie(id: String, view : View) {
//
//    }
    }


