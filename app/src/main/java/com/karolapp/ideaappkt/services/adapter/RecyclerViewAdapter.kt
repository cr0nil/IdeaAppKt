package com.karolapp.ideaappkt.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.services.holder.CurrencyHolder


class RecyclerViewAdapter(

    fragment: Fragment
) : RecyclerView.Adapter<CurrencyHolder>() {
    lateinit var mArrayList: ArrayList<Cryptocurrency>

    private val listener: RecyclerViewAdapter.onItemClickListener

    init {
        this.listener = fragment as RecyclerViewAdapter.onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {


        return CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(
                com.karolapp.ideaappkt.R.layout.layout_currency_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    fun setItems(rates: ArrayList<Cryptocurrency>) {
        mArrayList = rates
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val result = mArrayList.get(position)
        holder.bind(mArrayList[position])
//        holder.itemView.setOnClickListener {
//            Toast.makeText, position.toString(), Toast.LENGTH_LONG).show()
//        }

    }

    interface onItemClickListener {

        fun itemDetail(postId: String)
    }

}
