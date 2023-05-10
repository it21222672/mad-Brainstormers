package com.example.investordetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.investordetail.R
import com.example.investordetail.models.investModel

class investadapter(private val investList: ArrayList<investModel>)
    :RecyclerView.Adapter<investadapter.ViewHolder>(){

    private lateinit var mListener: onItemonclicklistener

    interface onItemonclicklistener{
        fun onItemclick(position: Int)
    }
    fun setonItemclicklistener(clickListener: onItemonclicklistener){
        mListener= clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.investname, parent , false)
        return ViewHolder(itemView , mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentInvs = investList[position]
        holder.tvinvestName.text = "welcome      " + currentInvs.investname
    }

    override fun getItemCount(): Int {
        return investList.size
    }



    class ViewHolder(itemView: View , clickListener: onItemonclicklistener): RecyclerView.ViewHolder(itemView) {
        val tvinvestName : TextView = itemView.findViewById(R.id.tvinvestName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemclick(adapterPosition)
            }
        }
    }
}