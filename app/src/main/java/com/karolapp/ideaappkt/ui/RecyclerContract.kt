package com.karolapp.ideaappkt.ui

import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.Rates

class RecyclerContract {
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(rates: Rates)
    //    fun loadDataAllSuccess(model: DetailsViewModel)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
      //  fun loadDataAll()
       // fun deleteItem(item: Post)
    }
}