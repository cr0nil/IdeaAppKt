package com.karolapp.ideaappkt.ui.view

//import com.karolapp.ideaappkt.di.DaggerFragmentComponent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crashlytics.android.Crashlytics
import com.google.android.material.navigation.NavigationView
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentHomeBinding
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.ItemHome
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import javax.inject.Inject


class HomeFragment : Fragment(), RecyclerContract.View, RecyclerViewAdapter.onItemClickListener {

    private var navigationView: NavigationView? = null
    private var navController: NavController? = null
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerViewAdapter


    @Inject
    lateinit var presenter: RecyclerContract.Presenter

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)
    }

    private val itemListenerMovie = object : ItemListener<Cryptocurrency> {
        override fun onClick(item: Cryptocurrency) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item.getName().toString())
            navController!!.navigate(action)
            presenter.getDetailsCurrency(item.name!!)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerAdapter = RecyclerViewAdapter(itemListenerMovie, this)
        presenter.attach(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        recyclerView = fragmentHomeBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.subscribe()
        initView()
    }


    private fun initView() {
     //   presenter.loadData(recyclerAdapter)
        presenter.getDetails()
       // presenter.getIcons(context!!)
    }

    override fun loadDataSuccess(rates: Rates, adapter: RecyclerViewAdapter) {
        var itemsHome: List<ItemHome>

     //   recyclerAdapter.setItems(rates.getCryptocurrencyList()!!)
        recyclerView.setAdapter(recyclerAdapter)
    }

    override fun loadIconSuccess(iconsCurrency: List<IconsCurrency>) {

        Log.i("log icons",iconsCurrency.toString())
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemDetail(postId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }
}
