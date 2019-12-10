package com.karolapp.ideaappkt.ui.presenter

import android.graphics.Color
import android.graphics.DashPathEffect
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
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
    var historicalData1: List<HistoricalData> = emptyList()

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)

    }
    override fun subscribe() {
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
Log.i("tututu",base_id)

        var subscribe = service.gerCryptoService().getHistoricalData(base_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                view.loadDataSuccess(it,dataSetToChart())
                historicalData1 = it},{
                throwable -> Crashlytics.logException(throwable)
            })


//
        subscriptions.add(subscribe)
    }


  fun dataSetToChart() :  LineDataSet{
        var i = 0
        Log.i("datga",historicalData1[0].time_open)
        val values: ArrayList<Entry> = ArrayList()
        for(i in 0..3){

            values.add(Entry(2f,1f))
        }
        val z = listOf(Entry(1f,2f), Entry(3f,4f), Entry(4f,4f))
        val   set1 = LineDataSet(z, "DataSet 1")
        set1.lineWidth = 1f
        set1.circleRadius = 3f

        // draw points as solid circles
        // draw points as solid circles
        set1.setDrawCircleHole(false)
        set1.setColor(Color.BLACK)
        set1.setCircleColor(Color.BLACK)
        // customize legend entry
        // customize legend entry
        set1.formLineWidth = 1f
        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        set1.formSize = 15f

        // text size of values
        // text size of values
        set1.valueTextSize = 9f

        // draw selection line as dashed
        // draw selection line as dashed
        set1.enableDashedHighlightLine(10f, 5f, 0f)

        // set the filled area
        // set the filled area
        set1.setDrawFilled(true)
      return set1
       // view.dataSetToChartSuccess(set1)
    }


}