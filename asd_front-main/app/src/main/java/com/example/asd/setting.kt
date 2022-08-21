package com.example.asd

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.discoding.SendUserInfo
import com.example.discoding.get_message
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class setting : AppCompatActivity() {


//    var gson= GsonBuilder().setLenient().create()
//    private val retrofit = Retrofit.Builder()
//            .baseUrl("http://selfstudy.kro.kr:5000/")
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()

//    private val service = retrofit.create(SendUserInfo::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setting)



        val sharedPreference = getSharedPreferences("uuid", 0)
        val editor  : SharedPreferences.Editor = sharedPreference.edit()

        val name = findViewById<EditText>(R.id.setting_userinfo_name).getText().toString()
        val age = findViewById<EditText>(R.id.setting_userinfo_age).getText().toString()
        val gender = findViewById<EditText>(R.id.setting_userinfo_gender).getText().toString()
        val code = findViewById<EditText>(R.id.setting_userinfo_code).getText().toString()
        val school = findViewById<EditText>(R.id.setting_userinfo_school).getText().toString()

        val setting_button = findViewById<Button>(R.id.setting_button)

        setting_button.setOnClickListener {
            if(findViewById<EditText>(R.id.setting_userinfo_name).getText().toString().equals("") ||
                findViewById<EditText>(R.id.setting_userinfo_age).getText().toString().equals("") ||
                findViewById<EditText>(R.id.setting_userinfo_gender).getText().toString().equals("") ||
                findViewById<EditText>(R.id.setting_userinfo_code).getText().toString().equals("") ||
                findViewById<EditText>(R.id.setting_userinfo_school).getText().toString().equals("") == null){

                Toast.makeText(this, "입력되지 않은 부분이 있습니다. 확인해주세요.", Toast.LENGTH_SHORT).show()
            }
            else{
                editor.putString("uuid", findViewById<EditText>(R.id.setting_userinfo_code).getText().toString())
                editor.commit()

                // 사용자 정보 저장
//                service.SendUserInfo(name, age, gender, code, school).enqueue(object :
//                    Callback<get_message> {
//                    override fun onResponse(
//                        call: Call<get_message>,
//                        response: Response<get_message>
//                    ) {
                        Toast.makeText(this, "성공적으로 사용자 정보가 등록되었습니다.", Toast.LENGTH_SHORT).show()
//                    }
//                    override fun onFailure(call: Call<get_message>, t: Throwable) {
//                        Log.d("result",t.toString())
//                    }
//                })
            }
        }





        val navigationbar_book = findViewById<ImageButton>(R.id.navigation_bar_book)
        val navigationbar_home = findViewById<ImageButton>(R.id.navigation_bar_home)

        navigationbar_book.setOnClickListener {
            val intent = Intent(this@setting, Calendar::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }
        navigationbar_home.setOnClickListener {
            val intent = Intent(this@setting, Main::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_left_exit)
        }

    }


}

