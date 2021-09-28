package com.example.customcalendar

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.customcalendar.databinding.ItemCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class CalendarRvAdapter(val context: Context, val mylist: ArrayList<Date>, val calendar: Calendar,val inputMonth: Int): RecyclerView.Adapter<CalendarRvAdapter.CustomViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(v: View, data: Date, pos: Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
    inner class CustomViewHolder(val binding: ItemCalendarBinding): RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SimpleDateFormat")
        fun onBind(item: Date) {
            val mFormat = SimpleDateFormat("dd")
            val day = calendar.get(Calendar.DATE)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            if (month!=inputMonth){
                binding.text.visibility=View.INVISIBLE
            }
            binding.text.text=mFormat.format(item)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding= ItemCalendarBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mylist.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.onBind(mylist[position])
    }



}