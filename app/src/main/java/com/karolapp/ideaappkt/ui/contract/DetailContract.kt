package com.karolapp.ideaappkt.ui.contract

import com.github.mikephil.charting.data.LineDataSet
import com.karolapp.ideaappkt.model.HistoricalData

class DetailContract {


    interface View : BaseContract.View {
        fun loadDataSuccess(historicalData: List<HistoricalData>,set: LineDataSet)
        fun dataSetToChartSuccess(set: LineDataSet)
        //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        //
        fun getHistoricalData(base_id: String)

       // fun dataSetToChart()
    }
}