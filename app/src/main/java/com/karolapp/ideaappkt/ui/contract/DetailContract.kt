package com.karolapp.ideaappkt.ui.contract

import com.github.mikephil.charting.data.LineDataSet
import com.karolapp.ideaappkt.model.HistoricalData

class DetailContract {


    interface View : BaseContract.View {
        fun loadDataSuccess(historicalData: List<HistoricalData>, set: LineDataSet)

    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getHistoricalData(base_id: String)

    }
}