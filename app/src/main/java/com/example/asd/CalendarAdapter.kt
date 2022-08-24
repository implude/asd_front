package com.example.asd

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.util.Calendar
import java.util.Calendar.getInstance

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
        var dateCalendar = Calendar.getInstance()

        var Month = dateCalendar.get(Calendar.MONTH) + 1

        // 오늘 날짜, (**/**)의 형식
        // ex) '8/21'
        //     '10/21' 등등
        val todayDate = "${Month}/${dayList[holder.adapterPosition]}"

        holder.dayText.text = dayList[holder.adapterPosition]

        holder.itemView.setOnClickListener{
            Log.e("fasfd", todayDate)
        }

    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}