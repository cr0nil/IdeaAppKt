package com.karolapp.ideaappkt.ui.view

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.ChartTouchListener
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentDetailsBinding
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.services.adapter.DetailAdapter
import com.karolapp.ideaappkt.ui.contract.DetailContract
import com.karolapp.ideaappkt.ui.presenter.MyYAxisValueFormatter
import javax.inject.Inject


class DetailsFragment : Fragment(), DetailContract.View, OnChartGestureListener {
    @Inject
    lateinit var presenter: DetailContract.Presenter

    private lateinit var fragmentDetailsBinding: FragmentDetailsBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: DetailAdapter
    lateinit var chartL: LineChart
    lateinit var progress : ProgressDialog

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerAdapter = DetailAdapter()
        presenter.attach(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Wait while loading...")
        progress.setCancelable(false) // disable dismiss by tapping outside of the dialog
        progress.show()
        chartL = fragmentDetailsBinding.chart
        recyclerView = fragmentDetailsBinding.recyclerViewDetail
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        val currencyName = DetailsFragmentArgs.fromBundle(arguments!!).currencyName
        presenter.subscribe()
        presenter.getHistoricalData(currencyName)


        return fragmentDetailsBinding.root
    }


    override fun loadDataSuccess(historicalData: List<HistoricalData>, set1: LineDataSet) {
        // chart wit historical data
        recyclerAdapter.setItems(historicalData)
        recyclerView.setAdapter(recyclerAdapter)
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        val data1 = LineData(dataSets)
        chartL.refreshDrawableState()

        set1.fillFormatter =
            IFillFormatter { dataSet: ILineDataSet, dataProvider: LineDataProvider ->
                chartL.getAxisLeft().getAxisMinimum()
            }

        setChart(data1)
        set1.notifyDataSetChanged()
        progress.dismiss()
    }

    override fun onChartGestureStart(
        me: MotionEvent,
        lastPerformedGesture: ChartTouchListener.ChartGesture
    ) {
    }

    override fun onChartGestureEnd(
        me: MotionEvent,
        lastPerformedGesture: ChartTouchListener.ChartGesture
    ) {
        chartL.highlightValues(null)
    }

    override fun onChartLongPressed(me: MotionEvent) {
    }

    override fun onChartDoubleTapped(me: MotionEvent) {
    }

    override fun onChartSingleTapped(me: MotionEvent) {

    }

    override fun onChartFling(
        me1: MotionEvent,
        me2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ) {
    }

    override fun onChartScale(me: MotionEvent, scaleX: Float, scaleY: Float) {
    }

    override fun onChartTranslate(me: MotionEvent, dX: Float, dY: Float) {
    }

    fun setChart(data: LineData) {
        chartL.setBackgroundColor(Color.BLACK)
        chartL.xAxis.textColor = Color.BLUE
        chartL.axisLeft.textColor = Color.BLUE

        chartL.setData(data)
        chartL.invalidate()
        chartL.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chartL.xAxis.valueFormatter = MyYAxisValueFormatter()
        chartL.setOnChartGestureListener(this)
    }

}
