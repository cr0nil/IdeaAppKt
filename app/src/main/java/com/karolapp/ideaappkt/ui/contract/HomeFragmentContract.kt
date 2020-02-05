package com.karolapp.ideaappkt.ui.contract

import android.content.Context
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter

class HomeFragmentContract {
    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(rates: Rates,listIconsCurrency: List<IconsCurrency>,adapter: RecyclerViewAdapter)
        //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getRepositoryCurrency(adapter: RecyclerViewAdapter)
    }
}