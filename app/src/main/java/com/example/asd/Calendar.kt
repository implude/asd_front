package com.example.asd

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.asd.databinding.CalendarBinding

class Calendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar)

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
}