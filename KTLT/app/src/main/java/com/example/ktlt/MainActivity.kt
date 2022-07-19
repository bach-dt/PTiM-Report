package com.example.ktlt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, tab2::class.java)
        object : CountDownTimer(1500, 100) {

            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                startActivity(intent)
            }
        }.start()
    }
}