package com.example.asd

import android.content.Intent
import android.icu.util.Calendar.getInstance
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asd.databinding.CalendarBinding
import com.example.asd.todos.Todo
import com.example.asd.todos.TodoViewModel
import com.example.asd.todos.ViewModelProviderFactory
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import androidx.lifecycle.Observer
import java.util.*
import java.util.Calendar.getInstance

class Calendar : AppCompatActivity() {

    // Todo List
    lateinit var todayAdapter : TodoAdapter
    lateinit var viewModel : TodoViewModel
    lateinit var todoList: MutableLiveData<MutableList<Todo>>

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


        // Todo List
        //뷰모델 받아오기
        viewModel = ViewModelProvider(this, ViewModelProviderFactory(this.application))
            .get(TodoViewModel::class.java)

        //recycler view에 보여질 아이템 Room에서 받아오기
        todoList = viewModel.mutableLiveData
        todoList.observe(this, Observer {
            todayAdapter.itemList = it
            todayAdapter.notifyDataSetChanged()
        })

        todayAdapter = TodoAdapter(this, mutableListOf<Todo>(), viewModel, ::setList)

        //recycler view에 adapter와 layout manager 넣기
        findViewById<RecyclerView>(R.id.recyclerView).adapter = todayAdapter
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = LinearLayoutManager(this)


        findViewById<Button>(R.id.add_button).setOnClickListener {
            if (findViewById<TextView>(R.id.recycleradd).text.toString() != "") {
                var Date = java.util.Calendar.getInstance()

                var year = Date.get(java.util.Calendar.YEAR)
                var month = Date.get(java.util.Calendar.MONTH) + 1
                var date = Date.get(java.util.Calendar.DATE)
                var time = "${year}-${month}-${date}"
                val todo = Todo(findViewById<TextView>(R.id.recycleradd).text.toString(), time, year, month, date)
                viewModel.insert(todo)
                setList()
                findViewById<TextView>(R.id.recycleradd).setText("")
            }
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
                //만약 일요일이 첫째날이 되버리면 일주일의 공백이 생긴 상태로 나오는 걸 방지
                if (dayOfWeek == 7 && i < 8) {
                    continue
                }
                dayList.add("")
            }
            else {
                dayList.add((i - dayOfWeek).toString())
            }
        }

        return dayList
    }


    // Todo List

    //메뉴 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setList()
        return false
    }


    // 화면을 다시 돌리기 위해 viewModel 내에 있는 LiveData의 value를 변경시켜줌.
    // value가 변경됨에 따라 observer에 설정된 함수가 실행되고 UI가 변경됨.
    fun setList() {
        todoList.value = viewModel.getList()
    }

}