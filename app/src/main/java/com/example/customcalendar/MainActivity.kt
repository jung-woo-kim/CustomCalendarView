package com.example.customcalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.customcalendar.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val calendar= Calendar.getInstance()
        binding.calendarView.setOnItemClickListener(object :CalendarView.OnItemClickListener{
            override fun onItemClick(v: View, year: Int, month: Int, day: Int) {

            }

        })
        binding.calendarView.updateCalendar(calendar)
    }
}