package com.example.asd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private val dayList: ArrayList<String>):
    RecyclerView.Adapter<CalendarAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val dayText: TextView = itemView.findViewById(R.id.calendar_cell_daytext)

    }

    //화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_cell, parent, false)

        return ItemViewHolder(view)
    }

    //데이터 설정
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder.dayText.text = dayList[holder.adapterPosition]
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}