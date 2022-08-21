package com.example.discoding


import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

data class get_message(
    val msg: String
)

interface SendUserInfo {
    @FormUrlEncoded
    @POST("/send_user_info")
    fun SendUserInfo(
        @Field("name") name: String,
        @Field("age") age: String,
        @Field("gender") gender: String,
        @Field("uuid") uuid: String,
        @Field("school") school: String,
    ): Call<get_message>

}