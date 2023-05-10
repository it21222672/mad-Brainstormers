package com.example.firebasekotlin.activities
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasekotlin.models.EmployeeModel
import com.example.firebasekotlin.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etEmpName: EditText
    private lateinit var etEmpTitle: EditText
    private lateinit var etEmpCnum: EditText
    private lateinit var etEmpDescription : EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference //assign variable for database referenece

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion) //set the respective UI(.xml file)

        var empportalback = findViewById<Button>(R.id.empback)
        empportalback.setOnClickListener(){
            var intent = Intent(this,EmployeeMainActivity::class.java)
            startActivity(intent)
        }
        //assigning values to the locations in the activity_insertion.xml
        etEmpName = findViewById(R.id.etEmpName)
        etEmpTitle = findViewById(R.id.etEmpTitle)
        etEmpCnum = findViewById(R.id.etEmpCnum)
        etEmpDescription = findViewById(R.id.etEmpDescription)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        btnSaveData.setOnClickListener {//save button
            saveEmployeeData() //calling the function
        }
    }

    private fun saveEmployeeData() {

        //getting values from the layout file
        val empName = etEmpName.text.toString()
        val empTitle = etEmpTitle.text.toString()
        val empCnum = etEmpCnum.text.toString()
        val empDescription = etEmpDescription.text.toString()
        //checking test fields are not empty
        if (empName.isEmpty()) {
            etEmpName.error = "Please enter name"
        }
        if (empTitle.isEmpty()) {
            etEmpTitle.error = "Please enter job title"
        }
        if (empCnum.isEmpty()) {
            etEmpCnum.error = "Please enter Contact number"
        }
        if (empDescription.isEmpty()) {
            etEmpDescription.error = "Please enter Description"
        }

        val empId = dbRef.push().key!! //create a reference to an auto generated child location

        val employee = EmployeeModel(empId, empName, empTitle, empCnum,empDescription)

        dbRef.child(empId).setValue(employee) //set the data at this location to the given values
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()//success message

                etEmpName.text.clear()
                etEmpTitle.text.clear()
                etEmpCnum.text.clear()
                etEmpDescription.text.clear()


            }.addOnFailureListener { err -> //adds a listner that is called if the task is failed
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show() //error message
            }

    }

}

