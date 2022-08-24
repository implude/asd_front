package com.example.asd.todos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Todo(var text: String?,var today: String?, var tyear: Int?, var tmonth: Int?,var tdate: Int?, var isDone: Boolean = false) : Serializable {
    // 등록하는 순간 System의 시간을 받으므로 등록일 순 정렬에 사용할 기준이 됨.
    @PrimaryKey
    var registerTime : Long = System.currentTimeMillis()

    @ColumnInfo
    var hashTag: String? = null

    // 날짜 기준 검색의 기준이 됨.
    @ColumnInfo
    var time: String? = null
    @ColumnInfo
    var date: String? = today
    @ColumnInfo
    var dateLong: Long? = null

    @ColumnInfo
    var year: Int? = tyear
    @ColumnInfo
    var month: Int? = tmonth
    @ColumnInfo
    var day: Int? = tdate
}