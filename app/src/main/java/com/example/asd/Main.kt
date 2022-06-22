package com.example.asd

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlin.reflect.typeOf

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // Turn on DND
        fun onDND(){
            if (checkNotificationPolicyAccess(notificationManager)){
                notificationManager.onDOD()
                toast("Do Not Disturb turned on.")
            }
        }

        // Turn off DND
        fun offDND(){
            if (checkNotificationPolicyAccess(notificationManager)){
                notificationManager.offDOD()
                toast("Do Not Disturb turned off")
            }
        }

    }
    //DND기능
    // Method to check notification policy access status
    private fun checkNotificationPolicyAccess(notificationManager:NotificationManager):Boolean{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (notificationManager.isNotificationPolicyAccessGranted){
                //toast("Notification policy access granted.")
                return true
            }else{
                toast("You need to grant notification policy access.")
                // If notification policy access not granted for this package
                val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
                startActivity(intent)
            }
        }else{
            toast("Device does not support this feature.")
        }
        return false
    }
    fun NotificationManager.onDOD(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE)
        }
    }


    // Extension function to turn off do not disturb
    fun NotificationManager.offDOD(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL)
        }
    }

    // Extension function to show toast message
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    }