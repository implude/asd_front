package com.example.asd

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.text.SpannableString
import android.text.style.ForegroundColorSpan


class Register :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val register_title = findViewById<TextView>(R.id.RegisterTitle)

        val str1 = "ASD"
        val str2 = " 회원가입"
        val spannable = SpannableString("$str1$str2")
        spannable.setSpan(ForegroundColorSpan(Color.parseColor("#000000")), str1.length, str1.length + str2.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        register_title.setText(spannable, TextView.BufferType.SPANNABLE)
    }
}