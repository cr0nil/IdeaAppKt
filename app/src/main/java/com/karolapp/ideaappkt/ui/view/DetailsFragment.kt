package com.karolapp.ideaappkt.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentDetailsBinding
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.services.adapter.DetailAdapter
import com.karolapp.ideaappkt.ui.contract.DetailContract
import javax.inject.Inject


class DetailsFragment : Fragment(), DetailContract.View {
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: DetailAdapter
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
        fragmentDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        recyclerView = fragmentDetailsBinding.recyclerViewDetail
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        Log.i("historical data", "dat")

      //  presenter.subscribe()
        presenter.getHistoricalData("BTC")


        return fragmentDetailsBinding.root
    }

    override fun loadDataSuccess(historicalData: List<HistoricalData>) {
        Log.i("historical data", historicalData.toString())
    }

}
