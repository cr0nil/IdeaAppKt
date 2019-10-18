package com.karolapp.ideaappkt.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentHomeBinding
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.services.api.ApiClient
import com.karolapp.ideaappkt.services.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class HomeFragment : Fragment() {
    private var navigationView: NavigationView? = null
    private var navController: NavController? = null
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView

    private var service: ApiService? = null
    val TAG = "home fragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        service = ApiClient.create()
        recyclerView = fragmentHomeBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        fragmentHomeBinding.homeFragment = this
        getData()
        return fragmentHomeBinding.root
    }

    private fun getData() {

        service!!.getCryptocurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                Consumer { t ->
                    displayCurrency(t)
                })


    }

    private fun displayCurrency(rates: Rates) {
        Log.i(TAG, rates.getCryptocurrencyList()!!.size.toString())
        recyclerView.adapter = RecyclerViewAdapter(context, rates.getCryptocurrencyList()!!)
    }


}
