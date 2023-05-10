package com.example.investordetail.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.investordetail.R
import com.example.investordetail.models.investModel
import com.example.investordetail.models.offetModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class insertperc : AppCompatActivity() {

    private lateinit var investperg: EditText
    private lateinit var investamount: EditText
    private lateinit var btnsave: Button
    private lateinit var bttonco: Button
    private lateinit var bttoncont:Button


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertperc)

        //remove action bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        //variable for activity use
        investperg = findViewById(R.id.investperg)
        investamount = findViewById(R.id.investamount)
        btnsave = findViewById(R.id.btnsave)
        bttonco = findViewById(R.id.bttonco)
        bttoncont = findViewById(R.id.bttoncont)
        //specify database path data
        dbRef = FirebaseDatabase.getInstance().getReference("InvestorOffer")

        bttonco.setOnClickListener{
            convertinvest()
        }

        btnsave.setOnClickListener{
            saveinvestordetail()
        }
        bttoncont.setOnClickListener{
            val intent = Intent(this , Contactus::class.java)
            startActivity(intent)
        }
    }



    private fun convertinvest(){
        val nu = findViewById<EditText>(R.id.investamount)
        val bttonco = findViewById<Button>(R.id.bttonco)


        // Get the EditText field
        val investamount = findViewById<EditText>(R.id.investamount)

       // Get the input as a string and convert it to a number
        val input = investamount.text.toString().toDouble()

       // Perform the calculations
        val result = input * 360

       // Display the result in a TextView
        val resultTextView = findViewById<TextView>(R.id.investcha)
        resultTextView.text = "Rs." + result.toString()

    }





    private fun saveinvestordetail(){
        //getting input

        val investpert = investperg.text.toString()
        val answ = investamount.text.toString()


        if(investpert.isEmpty()){
            investperg.error = "please enter the name"
        }
        if(answ.isEmpty()){
            investamount.error = "please enter the name"
        }

        //val investId = dbRef.push().key!!

        val investoffer = offetModel(investpert , answ)

        dbRef.child(investpert).setValue(investoffer)
            .addOnCompleteListener{
                Toast.makeText(this,"data insert successful" , Toast.LENGTH_LONG).show()

                //the clear data insert
                investperg.text.clear()
                investamount.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this,"error ${err.message}" , Toast.LENGTH_LONG).show()
            }
    }
}