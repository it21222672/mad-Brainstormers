package com.example.investorlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.investorlog.databinding.ActivitySetingBinding
import com.google.firebase.auth.FirebaseAuth

class seting : AppCompatActivity() {


    private lateinit var binding: ActivitySetingBinding
    private lateinit var user: FirebaseAuth
    private lateinit var settingbttnab: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()
        settingbttnab = findViewById(R.id.settingbttnab)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()


        if(user.currentUser != null){
            user.currentUser?.let {
                binding.tvuseremail.text = it.email
            }
        }


        binding.bttnsi.setOnClickListener{
            user.signOut()
            startActivity(
                Intent(this , signin::class.java)
            )

            finish()
        }

        settingbttnab.setOnClickListener{
            val intent = Intent(this,abot::class.java)
            startActivity(intent)
        }


    }
}