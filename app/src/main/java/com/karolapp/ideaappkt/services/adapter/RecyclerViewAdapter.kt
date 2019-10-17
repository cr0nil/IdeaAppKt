package com.karolapp.ideaappkt.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.services.holder.CurrencyHolder


class RecyclerViewAdapter() : RecyclerView.Adapter<CurrencyHolder>() {
    private var mContext: Context? = null
    private var mArrayList: ArrayList<Cryptocurrency>? = null
    //private var layoutInflater: LayoutInflater? = null
    //    private val onNoteListener: CurrencyHolder.OnNoteListener? = null
    private val currency: List<Cryptocurrency>? = null



    constructor(mContext: Context?, mArrayList: ArrayList<Cryptocurrency>?): this() {
        this.mContext = mContext
        this.mArrayList = mArrayList
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {


        return CurrencyHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.layout_currency_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return currency?.size ?: 0
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val cryptocurrency: Cryptocurrency = currency!!.get(position)
        // holder.bind(cryptocurrency)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}