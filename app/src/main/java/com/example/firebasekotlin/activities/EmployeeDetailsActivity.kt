//EmployeeDetails
package com.example.firebasekotlin.activities

import android.content.Intent
import com.example.firebasekotlin.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasekotlin.*
import com.example.firebasekotlin.models.EmployeeModel
import com.google.firebase.database.FirebaseDatabase

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpTitle: TextView
    private lateinit var tvEmpCnum: TextView
    private lateinit var tvEmpDescription: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details) //set the respective UI(.xml file)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {//update button
            openUpdateDialog(
                intent.getStringExtra("empId").toString(),
                intent.getStringExtra("empName").toString()
            )//calling update function
        }

        btnDelete.setOnClickListener {//delete button
            deleteRecord(
                intent.getStringExtra("empId").toString()
            )//calling delete function
        }

    }

    private fun initView() {
        //assign values to the components in the layout
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpTitle = findViewById(R.id.tvEmpTitle)
        tvEmpCnum = findViewById(R.id.tvEmpCnum)
        tvEmpDescription = findViewById(R.id.tvEmpDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() { //setting the values
        tvEmpId.text = intent.getStringExtra("empId")
        tvEmpName.text = intent.getStringExtra("empName")
        tvEmpTitle.text = intent.getStringExtra("empTitle")
        tvEmpCnum.text = intent.getStringExtra("empCnum")
        tvEmpDescription.text = intent.getStringExtra("empDescription")

    }

    private fun deleteRecord(//delete function
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Employee data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }








    private fun openUpdateDialog(//update function
        empId: String,
        empName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)
        //find respective components in the layout
        val etEmpName = mDialogView.findViewById<EditText>(R.id.etEmpName)
        val etEmpTitle = mDialogView.findViewById<EditText>(R.id.etEmpTitle)
        val etEmpCnum = mDialogView.findViewById<EditText>(R.id.etEmpCnum)
        val etEmpDescription = mDialogView.findViewById<EditText>(R.id.etEmpDescription)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etEmpName.setText(intent.getStringExtra("empName").toString())
        etEmpTitle.setText(intent.getStringExtra("empTitle").toString())
        etEmpCnum.setText(intent.getStringExtra("empCnum").toString())
        etEmpDescription.setText(intent.getStringExtra("empDescription").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                empId,
                etEmpName.text.toString(), //text values to Strings
                etEmpTitle.text.toString(),
                etEmpCnum.text.toString(),
                etEmpDescription.text.toString()
            )

            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            // setting updated data to  textviews
            tvEmpName.text = etEmpName.text.toString()
            tvEmpTitle.text = etEmpTitle.text.toString()
            tvEmpCnum.text = etEmpCnum.text.toString()
            tvEmpDescription.text = etEmpDescription.text.toString()
            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        id: String,
        name: String,
        title: String,
        cnum: String,
        description: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(id)//update in database
        val empInfo = EmployeeModel(id, name, title, cnum, description)
        dbRef.setValue(empInfo)
    }

}