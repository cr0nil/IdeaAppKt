package com.karolapp.ideaappkt.services.holder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.HistoricalData
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.model.ItemHome
import kotlinx.android.synthetic.main.layout_currency_item.view.*
import kotlinx.android.synthetic.main.layout_detail_item.view.*


class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
   lateinit var icon: ImageView
    fun bind(item: Cryptocurrency ) {
        with(itemView) {
            cryptocurrency_name.text = item.getName()
            cryptocurrency_rate.text = item.getRate()
            icon = itemView.findViewById(R.id.cryptocurrency_image)

        }
    }

    fun bindDetail(item: HistoricalData) {
        with(itemView) {
            time_open.text = item.time_open
            price_open.text = item.price_open.toString()

        }
    }


}