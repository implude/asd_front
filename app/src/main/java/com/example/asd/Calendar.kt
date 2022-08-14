package com.example.asd

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asd.databinding.CalendarBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class Calendar : AppCompatActivity() {

    private lateinit var binding: CalendarBinding

    //년월 변수
    lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar)

        //binding 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.calendar)

        //현재 날짜
        selectedDate = LocalDate.now()

        //화면 설정
        setMonthView()

        //이전달로 넘어가기
        binding.calendarCalendarDashLeft.setOnClickListener {
            //현재 월 - 1
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }

        //다음달로 넘어가기
        binding.calendarCalendarDashRight.setOnClickListener {
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }

        val navigationbar_book = findViewById<ImageButton>(R.id.navigation_bar_book)
        val navigationbar_home = findViewById<ImageButton>(R.id.navigation_bar_home)
        val navigationbar_info = findViewById<ImageButton>(R.id.navigation_bar_info)

        navigationbar_book.setOnClickListener {
            val intent = Intent(this@Calendar, Calendar::class.java)
            startActivity(intent)
        }
        navigationbar_home.setOnClickListener {
            val intent = Intent(this@Calendar, Main::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }
        navigationbar_info.setOnClickListener {
            val intent = Intent(this@Calendar, setting::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_right_enter, R.anim.slide_right_exit)
        }

    }

    //날짜 화면에 보여주기
    private fun setMonthView() {
        //년월 텍스트 셋팅
        binding.calendarCalendarYearnmonth.text = yearMonthFromDate(selectedDate)

        //날짜 생성해서 리스트에 담기
        val dayList = dayInMonthArray(selectedDate)

        //어댑터 초기화
        val adapter = CalendarAdapter(dayList)

        //레이아웃 설정(열 7개)
        val manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        //레이아웃 적용
        binding.calendarRecyclerview.layoutManager = manager

        //어댑터 적용
        binding.calendarRecyclerview.adapter = adapter
    }

    private fun yearMonthFromDate(date: LocalDate): String{
        var formatter = DateTimeFormatter.ofPattern("YYYY년 M월")

        //받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }

    //날짜 생성
    private fun dayInMonthArray(date: LocalDate): ArrayList<String> {
        val dayList = ArrayList<String>()

        var yearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜
        var lastDay = yearMonth.lengthOfMonth()

        //해당 월의 첫째 날
        var firstDay = selectedDate.withDayOfMonth(1)

        //첫 번째날 요일(월요일 : 1, 일요일 : 7)
        var dayOfWeek = firstDay.dayOfWeek.value

        for(i in 1..41) {
            if(i <= dayOfWeek || i > (lastDay + dayOfWeek)){
                dayList.add("")
            }
            else {
                dayList.add((i - dayOfWeek).toString())
            }
        }

        return dayList
    }
}