package com.karolapp.ideaappkt.services.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.karolapp.ideaappkt.R
import com.karolapp.ideaappkt.model.Cryptocurrency
import com.karolapp.ideaappkt.services.holder.CurrencyHolder


class RecyclerViewAdapter(
    private val mContext: Context,
    private val mArrayList: ArrayList<Cryptocurrency>,
    fragment: Fragment
) : RecyclerView.Adapter<CurrencyHolder>() {
//    private var mContext: Context? = null
//    private var mArrayList: ArrayList<Cryptocurrency>? = null
    //private var layoutInflater: LayoutInflater? = null


    private val listener: RecyclerViewAdapter.onItemClickListener

    init {
        this.listener = fragment as RecyclerViewAdapter.onItemClickListener
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
        return mArrayList.size
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        // val cryptocurrency: Cryptocurrency = currency!!.get(position)

        holder.bind(mArrayList[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(mContext, position.toString(), Toast.LENGTH_LONG).show()
        }

    }

    interface onItemClickListener {

        fun itemDetail(postId: String)
    }

}
