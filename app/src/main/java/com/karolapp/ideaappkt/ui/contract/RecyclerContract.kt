package com.karolapp.ideaappkt.ui.contract

import android.content.Context
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter

class RecyclerContract {
    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(rates: Rates, adapter: RecyclerViewAdapter)
        fun loadIconSuccess(iconsCurrency: List<IconsCurrency>)
        //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadData(adapter: RecyclerViewAdapter)
        fun getDetailsCurrency(id: String)
        fun getIcons(context: Context)
        fun getDetails()
        //  fun loadDataAll()
        // fun deleteItem(item: Post)
    }
}