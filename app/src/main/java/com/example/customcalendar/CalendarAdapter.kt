package com.example.customcalendar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class CalendarAdapter(context: Context, days: ArrayList<Date>, inputMonth: Int, calendar: Calendar) :
        ArrayAdapter<Date>(context, R.layout.calender_layout, days) {
    interface OnItemClickListener{
        fun onItemClick(v: View, year:Int,month:Int,day:Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var inputMonth = inputMonth - 1
    private val day1=days
    private val calender=calendar
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var view = view
        val calendar = calender
        val date = getItem(position)
        calendar.time = date
        val day = calendar.get(Calendar.DATE)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR) // 오늘에 해당하는 캘린더를 가져옴
        val today = Date()
        val calendarToday = Calendar.getInstance()
        calendarToday.time = today // 날짜 디자인으로 먼저 만들어 둔 calendar_day_layout을 inflate
        if (view == null) {
            view = inflater.inflate(R.layout.item_calendar, parent, false)
        } // 여기에서 기호에 따라 뷰의 생김새와 일자의 디자인을 변경이 가능.
        (view as TextView).setTypeface(null, Typeface.NORMAL)
        view.setTextColor(Color.parseColor("#595959")) // inputMonth는 ViewPager의 해당 페이지에 출력하는 Month를 표시.
        if (month != inputMonth) { // 아래의 경우 해당월이 아닌 경우에는 GridView에 표시되지 않도록 설정한 예.
            view.visibility=View.VISIBLE
            Log.d(view.text.toString(),"dsdsd")
        }
        if (month == calendarToday.get(Calendar.MONTH) && year == calendarToday.get(Calendar.YEAR) && day == calendarToday.get(Calendar.DATE)) { // 오늘의 날짜에 하고싶은 짓(?)을 정의 } // 날짜를 텍스트뷰에 설정

        }

        view.text = calendar.get(Calendar.DATE).toString()
        view.setOnClickListener {
            listener?.onItemClick(it,year, month+1, day)//클릭했을때 일어날 이벤트를 인터페이스로 연결해줌
        }
       return view
    }

}

