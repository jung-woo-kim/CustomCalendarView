package com.example.customcalendar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


import java.util.*
import kotlin.collections.ArrayList

class CalendarView: LinearLayout {
    interface OnItemClickListener{
        fun onItemClick(v: View, year:Int,month:Int,day:Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {

        this.listener = listener

    }
    lateinit var gridView: GridView


    var year=0
    var month=0
    var day=0

    constructor(context: Context) : this(context, null){
        initControl(context)
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initControl(context)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private fun assignUiElements() {
        gridView = findViewById(R.id.calendar)!!
    }


    fun updateCalendar(inputCalendar: Calendar) {
        val cells = ArrayList<Date>()

        this@CalendarView.year=inputCalendar.get(Calendar.YEAR)
        this@CalendarView.month=inputCalendar.get(Calendar.MONTH)+1
        this@CalendarView.day=inputCalendar.get(Calendar.DATE)

        inputCalendar.set(Calendar.DAY_OF_MONTH, 1)//전부 첫날짜를 1일로 바꿈

        // 여기서 빼주는 값 1의 경우 한 주의 시작요일에 따라 다르게 설정해주면 됨.
        //일요일부터 시작하는 관계로 1을 감산해주었음.
        val monthBeginningCell = inputCalendar.get(Calendar.DAY_OF_WEEK)-1

        inputCalendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell) // 그리드에 집어넣을 cell들의 setup.

        while (cells.size < (Calendar.DAY_OF_MONTH) + inputCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            cells.add(inputCalendar.time)
            inputCalendar.add(Calendar.DAY_OF_MONTH, 1)
        } // 그리드 업데이트


        val adapter=CalendarAdapter(context, cells,this.month,inputCalendar,)

        adapter.setOnItemClickListener(object :CalendarAdapter.OnItemClickListener{
            override fun onItemClick(v: View, year: Int, month: Int, day: Int) {
                listener?.onItemClick(v, year, month, day)
            }
        })

        gridView.adapter = adapter
    }

    @SuppressLint("ServiceCast")
    private fun initControl(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.calender_layout, this)
        assignUiElements()
    }
}


