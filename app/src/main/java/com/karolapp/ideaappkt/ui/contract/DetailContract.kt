package com.karolapp.ideaappkt.ui.contract

import com.karolapp.ideaappkt.model.HistoricalData

class DetailContract {


    interface View : BaseContract.View {
        fun loadDataSuccess(historicalData: List<HistoricalData>)
        //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        //
        fun getHistoricalData(base_id: String)
    }
}