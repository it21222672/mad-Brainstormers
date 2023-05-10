package com.example.firebasekotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.firebasekotlin.R

class Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val calButton = findViewById<Button>(R.id.calbutton)
        calButton.setOnClickListener {
            val offerEditText = findViewById<EditText>(R.id.Offer)
            val percentageEditText = findViewById<EditText>(R.id.precentageinput)
            val profitEditText = findViewById<EditText>(R.id.profitinput)

            val offer = offerEditText.text.toString().toDouble()
            val percentage = percentageEditText.text.toString().toDouble()
            val profit = profitEditText.text.toString().toDouble()

            val result = profit - (offer * (percentage / 100))
            val message = if (result >= 0) "OK" else "Not OK"

            val intent = Intent(this, Answer::class.java)
            intent.putExtra("result", result)
            intent.putExtra("message", message)
            startActivity(intent)
        }

        val calBackButton = findViewById<Button>(R.id.calbackbutton)
        calBackButton.setOnClickListener {
            val intent = Intent(this, EmployeeMainActivity::class.java)
            startActivity(intent)
        }
    }
}
