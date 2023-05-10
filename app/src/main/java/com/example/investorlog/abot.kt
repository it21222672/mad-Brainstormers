package com.example.investorlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class abot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abot)



        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
    }
}