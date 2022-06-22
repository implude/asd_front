package com.example.asd

import User_identification_router
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        //우리 서버 주소
//        val url = null

        // retrofit2 server connection part. login, register etc ..
//        val retrofit = Retrofit.Builder().baseUrl(url.toString())
//            .addConverterFactory(GsonConverterFactory.create()).build();
//        val service = retrofit.create(User_identification_router::class.java);


        // check
        findViewById<Button>(R.id.login_button).setOnClickListener{
            val intent = Intent(this@Login, Main::class.java)
            startActivity(intent)
        }
    }
}

