package com.karolapp.ideaappkt.services.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.holder.CurrencyHolder

class DetailAdapter(private val itemListener: ItemListener<HistoricalData>
                    ): RecyclerView.Adapter<CurrencyHolder>(){
    lateinit var mArrayList: List<HistoricalData>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {


        return CurrencyHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_detail_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mArrayList.size
    }

    fun setItems(rates: List<HistoricalData>) {
        mArrayList = rates
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val result = mArrayList.get(position)
        holder.bindDetail( mArrayList[position])
//        itemListener
        holder.itemView.setOnClickListener {
            itemListener.onClick(result)
        }

    }
//    fun replaceData(data: List<HistoricalData>) {
//        this.mArrayList.clear()
//        this.mArrayList.addAll(data)
//        notifyDataSetChanged()
//    }
//
//    fun addMoreItem(data: List<HistoricalData>) {
//        this.mArrayList.addAll(data)
//        notifyDataSetChanged()
//    }
    interface onItemClickListener {

        fun itemDetail(postId: String)
    }

}
