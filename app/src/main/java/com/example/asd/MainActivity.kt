package com.example.asd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val togo_find_id = findViewById<TextView>(R.id.find_id)
        val togo_find_pw = findViewById<TextView>(R.id.find_pw)
        val togo_register = findViewById<TextView>(R.id.register)

    }
}

