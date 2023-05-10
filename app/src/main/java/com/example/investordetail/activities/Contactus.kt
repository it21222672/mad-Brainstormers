package com.example.investordetail.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.investordetail.R
import com.example.investordetail.databinding.ActivityContactusBinding

class Contactus : AppCompatActivity() {

    lateinit var edtemail: EditText
    lateinit var edtsubject: EditText
    lateinit var edtmessage: EditText

    lateinit var bttoncontus:Button

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus)


        edtemail = findViewById(R.id.editTextc)
        edtsubject = findViewById(R.id.editTexta)
        edtmessage = findViewById(R.id.editTextb)
        bttoncontus = findViewById(R.id.bttoncontus)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        bttoncontus.setOnClickListener{
            var emailid = edtemail.text.toString().trim()
            var subject = edtsubject.text.toString().trim()
            var message = edtmessage.text.toString().trim()



            var i = Intent(Intent.ACTION_SEND)
            i.data =Uri.parse("mailto")
            i.type = "text/plain"


                i.putExtra(Intent.EXTRA_EMAIL , arrayOf(emailid))
                i.putExtra(Intent.EXTRA_SUBJECT , subject)
                i.putExtra(Intent.EXTRA_TEXT , message)
            startActivity(Intent.createChooser(i ,"choose email the client"))
            }



        }
}