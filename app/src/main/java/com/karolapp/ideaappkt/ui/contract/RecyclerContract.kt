package com.karolapp.ideaappkt.ui.contract

import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter

class RecyclerContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(rates: Rates, adapter: RecyclerViewAdapter)
    //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(adapter: RecyclerViewAdapter)
      //  fun loadDataAll()
       // fun deleteItem(item: Post)
    }
}