package com.karolapp.ideaappkt.ui.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karolapp.ideaappkt.CryptocurrenycyAplication

import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.ui.contract.DetailContract
import javax.inject.Inject


class DetailsFragment : Fragment(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter
    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("historical data", "dat")

      //  presenter.subscribe()
        presenter.getHistoricalData("BTC")

        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun loadDataSuccess(historicalData: HistoricalData) {
        Log.i("historical data", historicalData.toString())
    }

}
