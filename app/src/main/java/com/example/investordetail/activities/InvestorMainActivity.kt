package com.example.investordetail.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.investordetail.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InvestorMainActivity : AppCompatActivity() {


    private lateinit var insertbtton: Button
    private lateinit var fetchbtton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()

        insertbtton = findViewById(R.id.insertbtton)
        fetchbtton = findViewById(R.id.fetchbtton)


        insertbtton.setOnClickListener{
            val intent = Intent(this , forminvestor::class.java)
            startActivity(intent)
        }

        fetchbtton.setOnClickListener{
            val intent = Intent(this , displayinvestor::class.java)
            startActivity(intent)
        }

    }
}