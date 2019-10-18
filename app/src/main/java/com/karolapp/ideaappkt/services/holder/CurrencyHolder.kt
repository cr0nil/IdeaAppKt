package com.karolapp.ideaappkt.services.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.model.Cryptocurrency
import kotlinx.android.synthetic.main.layout_currency_item.view.*


class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bind(item: Cryptocurrency) {
        with(itemView) {
            cryptocurrency_name.text = item.getName()
            cryptocurrency_rate.text = item.getRate()

        }
    }


}