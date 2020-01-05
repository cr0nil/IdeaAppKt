package com.karolapp.ideaappkt.ui.view


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import com.karolapp.ideaappkt.CryptocurrenycyAplication
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.databinding.FragmentHomeBinding
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.contract.RecyclerContract
import javax.inject.Inject


class HomeFragment : Fragment(), RecyclerContract.View, RecyclerViewAdapter.onItemClickListener,
    SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var navigationView: NavigationView? = null
    private var navController: NavController? = null
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerViewAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var searchView: SearchView
    lateinit var item: MenuItem


    @Inject
    lateinit var presenter: RecyclerContract.Presenter

    init {
        CryptocurrenycyAplication.cryptocurrencyApplicationComponent.inject(this)
    }

    private val itemListenerCurrencyItem = object : ItemListener<Cryptocurrency> {
        override fun onClick(item: Cryptocurrency) {
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item.getName().toString())
            navController!!.navigate(action)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerAdapter = RecyclerViewAdapter(itemListenerCurrencyItem, this)
        presenter.attach(this)
        setHasOptionsMenu(true)

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
        swipeRefreshLayout = fragmentHomeBinding.swiperefresh
        swipeRefreshLayout.setOnRefreshListener(this)
//        swipeRefreshLayout.setColorSchemeResources(
//            R.color.holo_green_dark,
//            R.color.holo_orange_dark,
//            R.color.holo_blue_dark
//        )


//        swipeRefreshLayout.post(Runnable {
//            swipeRefreshLayout.setRefreshing(true)
//            // Fetching data from server
//            initView()
//        })
//        (activity as (MainActivity) )
//            .setActionBarTitle("Your title")
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


    fun initView() {
        presenter.getRepositoryCurrency(recyclerAdapter)
    }

    override fun loadDataSuccess(
        rates: Rates,
        iconsCurrency: List<IconsCurrency>,
        adapter: RecyclerViewAdapter
    ) {
        recyclerAdapter.setItems(rates.cryptocurrencyList!!, iconsCurrency, context!!)
        recyclerView.setAdapter(recyclerAdapter)
        swipeRefreshLayout.setRefreshing(false)
    }


    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        Log.i("error", error)
        fragmentHomeBinding.refresh.visibility = View.VISIBLE
    }

    override fun itemDetail(postId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onRefresh() {

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        item = menu.findItem(R.id.search)
        searchView = item.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerAdapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerAdapter.filter.filter(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }


}
