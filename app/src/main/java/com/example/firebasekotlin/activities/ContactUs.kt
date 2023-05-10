package com.example.firebasekotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasekotlin.R
import com.example.firebasekotlin.models.ContactModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ContactUs : AppCompatActivity() {

    lateinit var etContactName: EditText
    lateinit var etContactEmail: EditText

    lateinit var etContactDescription : EditText
    private lateinit var btnSaveData: Button

     lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)//set the UI(.xml file)




            var contactportalback = findViewById<Button>(R.id.ContactCbutton) //back button
            contactportalback.setOnClickListener(){
                var intent = Intent(this,EmployeeMainActivity::class.java)
                startActivity(intent)
            }
            etContactName = findViewById(R.id.inputField1)
            etContactEmail = findViewById(R.id.emailinput)
            etContactDescription = findViewById(R.id.inputField2)
            btnSaveData = findViewById(R.id.ContactSbutton)

            dbRef = FirebaseDatabase.getInstance().getReference("Contact") //assign variable for database referenece

            btnSaveData.setOnClickListener {
                saveContactData()
            }
        }

        fun saveContactData() {

            //getting values
            val contactName = etContactName.text.toString()
            val contactEmail = etContactEmail.text.toString()
            val contactDescription = etContactDescription.text.toString()

            if (contactName.isEmpty()) {
                etContactName.error = "Please enter name"
            }
            if (contactEmail.isEmpty()) {
                etContactEmail.error = "Please enter email"
            }

            if (contactDescription.isEmpty()) {
                etContactDescription.error = "Please enter salary"
            }

            val contactId = dbRef.push().key!! //create a reference to an auto generated child location

            val contact = ContactModel(contactId,contactName, contactEmail, contactDescription)

            dbRef.child(contactId).setValue(contact)
                .addOnCompleteListener {
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()//success message

                    etContactName.text.clear()
                    etContactEmail.text.clear()
                    etContactDescription.text.clear()


                }.addOnFailureListener { err ->//adds a listner that is called if the task is failed
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        }


}
