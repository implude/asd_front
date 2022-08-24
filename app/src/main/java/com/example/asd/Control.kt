package com.example.asd

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

data class get_message(
    val msg: String
)
interface Control{
    @FormUrlEncoded
    @POST("/control_value")
    fun SendControlValue(
        @Field("ledValue") ledValue : Int,
        @Field("speakerValue") speakerValue : Int
    ): Call<get_message>
}