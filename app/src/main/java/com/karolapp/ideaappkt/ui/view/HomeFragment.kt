package com.karolapp.ideaappkt.ui.view

import android.os.Bundle
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
import com.karolapp.ideaappkt.di.DaggerFragmentComponent
import com.karolapp.ideaappkt.di.FragmentModule
import com.karolapp.ideaappkt.model.Rates
import com.karolapp.ideaappkt.services.adapter.RecyclerViewAdapter
import com.karolapp.ideaappkt.ui.RecyclerContract
import javax.inject.Inject


class HomeFragment : Fragment(), RecyclerContract.View, RecyclerViewAdapter.onItemClickListener {
    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showErrorMessage(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemDetail(postId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var navigationView: NavigationView? = null
    private var navController: NavController? = null
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView

    private lateinit var rootView: View
    @Inject
    lateinit var presenter: RecyclerContract.Presenter
    val TAG = "home fragment"

    fun newInstance(): HomeFragment {
        return HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
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

        // getData()
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }



    private fun initView() {
        presenter.loadData()
    }

    override fun loadDataSuccess(rates: Rates) {

        var adapter = RecyclerViewAdapter(context!!, rates.getCryptocurrencyList()!!, this)

        recyclerView.setAdapter(adapter)

//        val swipeHandler = object : SwipeToDelete(actiity) {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val adapter = recyclerView.adapter as RecyclerViewAdapter
//                adapter.removeAt(viewHolder.adapterPosition)
//            }
//        }

        //  val itemTouchHelper = ItemTouchHelper(swipeHandler)
        // itemTouchHelper.attachToRecyclerView(recyclerView)
    }


//    private fun displayCurrency(rates: Rates) {
//        Log.i(TAG, rates.getCryptocurrencyList()!!.size.toString())
//        recyclerView.adapter = RecyclerViewAdapter(context, rates.getCryptocurrencyList()!!)
//    }

    private fun injectDependency() {

        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }
}
