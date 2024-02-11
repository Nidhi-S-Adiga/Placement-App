package com.example.bmsce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmsce.R
import com.example.bmsce.models.CompanyModel

class CmpAdapter (private val cmplist:ArrayList<CompanyModel>) : RecyclerView.Adapter<CmpAdapter.ViewHolder>(){

    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: OnItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cmp_list_item,parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val currentCmp = cmplist[position]
        holder.tvCmpName.text=currentCmp.cmpname
    }

    override fun getItemCount(): Int {
        return cmplist.size
    }


    class ViewHolder (itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvCmpName : TextView = itemView.findViewById(R.id.tvCmpName)

        init{
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}