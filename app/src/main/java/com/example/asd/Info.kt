package com.example.asd

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

class Info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)

        val navigationbar_book = findViewById<ImageButton>(R.id.navigation_bar_book)
        val navigationbar_home = findViewById<ImageButton>(R.id.navigation_bar_home)
//        val navigationbar_info = findViewById<ImageButton>(R.id.navigation_bar_info)

        navigationbar_book.setOnClickListener {
            val intent = Intent(this@Info, Calendar::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
        navigationbar_home.setOnClickListener {
            val intent = Intent(this@Info, Main::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
//        navigationbar_info.setOnClickListener {
//            val intent = Intent(this@Info, Info::class.java)
//            startActivity(intent)
//        }

    }
}

