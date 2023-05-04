package com.example.homepage.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.models.BusinessModel

class BusinessAdopter(private val empList : ArrayList<BusinessModel>) :
    RecyclerView.Adapter<BusinessAdopter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener

    // Interface to handle clicks on RecyclerView items
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    // Function to set click listener for RecyclerView items
    fun setOnClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessAdopter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_business_list_item, parent, false)
        return ViewHolder(itemView,mListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvBusinessName.text = currentEmp.businessName
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return empList.size
    }

    // Provide a reference to the views for each data item
    class ViewHolder (itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvBusinessName : TextView = itemView.findViewById(R.id.tvBusinessName)

        // Initialize the click listener for the view
        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }

    }
}