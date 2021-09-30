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
        setContentView(binding.root)//필수!!
        val calendar= Calendar.getInstance()//원하는 달을 가지고 잇는 캘린더
        binding.calendarView.setOnItemClickListener(object :CalendarView.OnItemClickListener{
            override fun onItemClick(v: View, year: Int, month: Int, day: Int) {
                //각 날짜를 클릭했을 때 이벤트 설정
                //interface를 통해서 연결해줌

            }

        })
        binding.calendarView.updateCalendar(calendar)//해줘야 달력이 보임 원하는 달의 날짜들을 볼 수 있다.
    }
}