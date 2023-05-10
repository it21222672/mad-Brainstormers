package com.example.investordetail.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.investordetail.R
import com.example.investordetail.models.investModel
import com.google.firebase.database.FirebaseDatabase

private lateinit var tvinvestid: TextView
private lateinit var tvinvestname: TextView
private lateinit var tvinvestnic: TextView
private lateinit var tvinvestcountry: TextView
private lateinit var bttonupdate: Button
private lateinit var bttondelete: Button
private lateinit var bttona: Button

class investordetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investordetail)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        initView()
        setValuesToViews()

        bttona.setOnClickListener{
            val intent = Intent(this , insertperc::class.java)
            startActivity(intent)
        }

        bttonupdate.setOnClickListener{
            openupdatedialog(
                intent.getStringExtra("investId").toString(),
                intent.getStringExtra("investName").toString()
            )
        }

        bttondelete.setOnClickListener{
            deleterecord(
                intent.getStringExtra("investId").toString()
            )
        }



    }

    private fun deleterecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Investor").child(id)
        val mtask = dbRef.removeValue()


        mtask.addOnSuccessListener {
            Toast.makeText(this , "investor data delete" , Toast.LENGTH_LONG).show()



            val intent = Intent(this , displayinvestor::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this , "delete error ${error.message}" , Toast.LENGTH_LONG).show()
        }
    }


    private fun initView() {
        tvinvestid = findViewById(R.id.tvinvestid)
        tvinvestname = findViewById(R.id.tvinvestname)
        tvinvestnic = findViewById(R.id.tvinvestnic)
        tvinvestcountry = findViewById(R.id.tvinvetcountry)

        bttonupdate = findViewById(R.id.bttonupdate)
        bttondelete = findViewById(R.id.bttondelete)
        bttona = findViewById(R.id.bttona)
    }

    private fun setValuesToViews() {
        tvinvestid.text = intent.getStringExtra("investId")
        tvinvestname.text = intent.getStringExtra("investName")
        tvinvestnic.text = intent.getStringExtra("investNic")
        tvinvestcountry.text = intent.getStringExtra("investCountry")

    }

    //update
    private fun openupdatedialog(
        investId: String,
        investName: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogview = inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogview)

        val etinvestname = mDialogview.findViewById<EditText>(R.id.etinvestname)
        val etinvestnic = mDialogview.findViewById<EditText>(R.id.etinvestnic)
        val etinvestcountry = mDialogview.findViewById<EditText>(R.id.etinvestcountry)
        val bttonupdatdata = mDialogview.findViewById<Button>(R.id.bttonupdatdata)

        etinvestname.setText(intent.getStringExtra("investName").toString())
        etinvestnic.setText(intent.getStringExtra("investNic").toString())
        etinvestcountry.setText(intent.getStringExtra("investCountry").toString())
        //set title to dialog
        mDialog.setTitle("updating record")


        //show dialog
        val alertDialog = mDialog.create()
        alertDialog.show()



        bttonupdatdata.setOnClickListener{
            updateinvestdata(
                investId,
                etinvestname.text.toString(),
                etinvestnic.text.toString(),
                etinvestcountry.text.toString()
            )

            Toast.makeText(applicationContext, "investor data update" , Toast.LENGTH_LONG).show()

            //the set update data to text
            tvinvestname.text = etinvestname.text.toString()
            tvinvestnic.text = etinvestnic.text.toString()
            tvinvestcountry.text = etinvestcountry.text.toString()


            alertDialog.dismiss()

        }

    }

    private fun updateinvestdata(
        id: String,
        name: String,
        nic: String,
        country: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Investor").child(id)
        val empInfo = investModel(id , name , nic , country)
        dbRef.setValue(empInfo)
    }



}
