package com.karolapp.ideaappkt.ui.presenter

import android.graphics.Color
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.karolapp.ideaappkt.BaseApplication
import com.karolapp.ideaappkt.di.Component.DaggerAppComponent
import com.karolapp.ideaappkt.di.Component.DaggerHomeFragmentComponent
import com.karolapp.ideaappkt.di.Module.AppModule
import com.karolapp.ideaappkt.di.Module.HomeFragmentModule
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.services.api.ApiClient
import com.karolapp.ideaappkt.ui.contract.DetailContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailPreseter @Inject constructor(private val service: ApiClient) : DetailContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: DetailContract.View

//    init {
//        BaseApplication.appApplicationComponent.inject(this)
//
//    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: DetailContract.View) {
        this.view = view
    }

    override fun getHistoricalData(base_id: String) {
//        val service =
//            DaggerHomeFragmentComponent.builder().homeFragmentModule(HomeFragmentModule(this))
//        Log.i("tututu", base_id)

        var subscribe = service.create().getHistoricalData(base_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ it ->
                view.loadDataSuccess(it, dataSetToChart(it))
            }, { throwable ->
                Crashlytics.logException(throwable)
            })

        subscriptions.add(subscribe)
    }


    fun dataSetToChart(historicalData1: List<HistoricalData>): LineDataSet {
        var i = 0
        Log.i("datga", historicalData1.size.toString())
        var inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        var date: Long?
        val values: ArrayList<Entry> = ArrayList()
        for (i in 0..60) {

            date = inputFormat.parse(historicalData1[i].time_open).time
            values.add(Entry(date.toFloat(), historicalData1[i].price_open.toFloat()))
        }

        val set1 = LineDataSet(values, "DataSet 1")
//        set1.lineWidth = 1f
//        set1.circleRadius = 3f

        // draw points as solid circles
        set1.setDrawCircleHole(false)
        set1.highLightColor = Color.BLUE
        set1.valueTextColor = Color.BLUE
        set1.setColor(Color.BLUE)
        set1.setCircleColor(Color.BLUE)
        val format = MyYAxisValueFormatter()
        //set1.setValueFormatter(format)
        // customize legend entry
        //  set1.formLineWidth = 1f
//        set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        //     set1.formSize = 30f

        // text size of values
        set1.valueTextSize = 9f
        // draw selection line as dashed
        //  set1.enableDashedHighlightLine(10f, 5f, 0f)
        // set the filled area
        set1.setDrawFilled(true)
        return set1

    }


}

class MyYAxisValueFormatter : IAxisValueFormatter {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        Log.i("time data",dateFormat.toString())
        return dateFormat.format(value.toLong())
    }


}