package com.karolapp.ideaappkt.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.model.IconsCurrency
import com.karolapp.ideaappkt.services.ItemListener
import com.karolapp.ideaappkt.services.holder.CurrencyHolder


class RecyclerViewAdapter(
    private val itemListener: ItemListener<Cryptocurrency>,
    private val fragment: Fragment
) : RecyclerView.Adapter<CurrencyHolder>() {
    lateinit var mArrayList: List<Cryptocurrency>
    lateinit var mArrayListIcons: List<IconsCurrency>
    lateinit var context1 : Context

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

    fun setItems(rates: List<Cryptocurrency>,icons:List<IconsCurrency>,context: Context) {
        mArrayList = rates
        mArrayListIcons = icons
        context1 = context
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val result = mArrayList.get(position)

        holder.bind(mArrayList[position])
        holder.itemView.setOnClickListener {
           itemListener.onClick(result)
        }
        for(icon in mArrayListIcons){
            if((icon.asset_id) == mArrayList.get(position).name){
                Glide
                    .with(context1)
                    .load(icon.url)
                    .circleCrop()
                    .into(holder.icon)
            }

        }

    }

    interface onItemClickListener {

        fun itemDetail(postId: String)
    }

}
