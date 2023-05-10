package com.example.firebasekotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.firebasekotlin.R

class Answer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val result = intent.getDoubleExtra("result", 0.0)
        val message = intent.getStringExtra("message")

        val answerTextView = findViewById<TextView>(R.id.answerdisplay)
        answerTextView.text = if (result >= 0) "OK" else "Not OK"
    }
}
