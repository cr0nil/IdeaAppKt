package com.karolapp.ideaappkt.ui.view

import android.graphics.Color
import android.graphics.DashPathEffect
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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentDetailsBinding
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.adapter.DetailAdapter
import com.karolapp.ideaappkt.ui.contract.DetailContract
import javax.inject.Inject


class DetailsFragment : Fragment(), DetailContract.View {
    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: DetailAdapter
    @Inject
    lateinit var presenter: DetailContract.Presenter
    lateinit var chartL:LineChart
    var historicalData1: List<HistoricalData> = emptyList()
    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)
    }

    private val itemListenerDetail = object : ItemListener<HistoricalData> {
        override fun onClick(item: HistoricalData) {
//val action =
//            navController!!.navigate(R.id.action_homeFragment_to_detailsFragment)
//            presenter.getDetailsCurrency(item.name!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerAdapter = DetailAdapter(itemListenerDetail)
        presenter.attach(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        recyclerView = fragmentDetailsBinding.recyclerViewDetail
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        val x = DetailsFragmentArgs.fromBundle(arguments!!).currencyName
        Log.i("text", x)
        presenter.subscribe()
        presenter.getHistoricalData(x)




        return fragmentDetailsBinding.root
    }


    override fun dataSetToChartSuccess(set1: LineDataSet) {


    }

    override fun loadDataSuccess(historicalData: List<HistoricalData>,set1: LineDataSet) {
        Log.i("historical data", "dat")
        recyclerAdapter.setItems(historicalData)

        recyclerView.setAdapter(recyclerAdapter)

        Log.i("set","Ser")
        chartL= fragmentDetailsBinding.chart
        set1.fillFormatter =
            IFillFormatter { dataSet, dataProvider -> chartL.getAxisLeft().getAxisMinimum() }
        val dataSets =  ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data1 = LineData(dataSets)



        set1.notifyDataSetChanged()
        chartL.setData(data1)
    }

}
