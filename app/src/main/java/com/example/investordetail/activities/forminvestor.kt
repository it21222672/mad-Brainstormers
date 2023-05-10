package com.example.investordetail.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.investordetail.R
import com.example.investordetail.models.investModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class forminvestor : AppCompatActivity() {
    private lateinit var edittxt1:EditText
    private lateinit var edittxt2:EditText
    private lateinit var edittxt3:EditText
    private lateinit var bttonaa:Button

    private lateinit var bttonin:Button

    //create the database object
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forminvestor)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        //create varible to text
        edittxt1 = findViewById(R.id.edittxt1)
        edittxt2 = findViewById(R.id.edittxt2)
        edittxt3 = findViewById(R.id.edittxt3)
        bttonaa = findViewById(R.id.bttonaa)
        bttonin = findViewById(R.id.bttonin)
        //specify database path data
        dbRef = FirebaseDatabase.getInstance().getReference("Investor")


        bttonaa.setOnClickListener{
            saveinvestordetail()
        }
        bttonin.setOnClickListener{
            val intent = Intent(this , displayinvestor::class.java)
            startActivity(intent)
        }
    }

    private fun saveinvestordetail(){
        //getting input

        val investname = edittxt1.text.toString()
        val investnic = edittxt2.text.toString()
        val investcountry = edittxt3.text.toString()
        //check text is empty
        if(investname.isEmpty()){
            edittxt1.error = "please enter the name"
        }
        if(investnic.isEmpty()){
            edittxt2.error = "please enter the name"
        }
        if(investcountry.isEmpty()){
            edittxt3.error = "please enter the name"
        }
        //push investid to data
        val investId = dbRef.push().key!!

        val invest = investModel(investId,investname,investnic,investcountry)

        dbRef.child(investId).setValue(invest)
            .addOnCompleteListener{
                Toast.makeText(this,"data insert successful" , Toast.LENGTH_LONG).show()

                edittxt1.text.clear()
                edittxt2.text.clear()
                edittxt3.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this,"error ${err.message}" , Toast.LENGTH_LONG).show()
            }
    }
}