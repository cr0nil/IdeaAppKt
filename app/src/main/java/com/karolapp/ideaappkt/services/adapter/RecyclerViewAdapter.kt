package com.karolapp.ideaappkt.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.holder.CurrencyHolder


class RecyclerViewAdapter(
    private val itemListener: ItemListener<Cryptocurrency>,
    private val fragment: Fragment
) : RecyclerView.Adapter<CurrencyHolder>() {
    lateinit var mArrayList: ArrayList<Cryptocurrency>

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
//        itemListener
        holder.itemView.setOnClickListener {
            Toast.makeText(fragment.context, position.toString(), Toast.LENGTH_LONG).show()
        }

    }

    interface onItemClickListener {

        fun itemDetail(postId: String)
    }

}
