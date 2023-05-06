package com.example.homepage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.homepage.R
import com.example.homepage.models.BusinessModel
import com.google.firebase.database.FirebaseDatabase

class DetailsView : AppCompatActivity() {

    // Declare the views as private properties
    private lateinit var tvBusinessId: TextView
    private lateinit var tvBusinessName: TextView
    private lateinit var tvOwnerName: TextView
    private lateinit var tvPostAddress: TextView
    private lateinit var tvRegNum: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        // Set the system UI visibility and hide the action bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_view)

        // Initialize the views
        initView()

        // Set the values of the views based on the intent extras
        setValuesToView()

        // Set the click listeners for the update and delete buttons
        btnUpdate.setOnClickListener{
            // When the update button is clicked, open the update dialog
            openUpdateDialog(
                intent.getStringExtra("businessId").toString(),
                intent.getStringExtra("businessName").toString()
            )
        }

        btnDelete.setOnClickListener {
            // When the delete button is clicked, show an alert dialog to confirm deletion
            AlertDialog.Builder(this)
                .setTitle("Delete Business Data")
                .setMessage("Are you sure you want to delete this record?")
                .setPositiveButton("Yes") { _, _ ->
                    // If the user confirms deletion, call the deleteRecord function with the businessId as parameter
                    deleteRecord(intent.getStringExtra("businessId").toString())
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    // Function to delete the record from the database
    private fun deleteRecord(
        businessId: String
    ){
        // Get a reference to the "Employees" node in the database and remove the value at the given businessId
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(businessId)
        val mTask = dbRef.removeValue()

        // Set the success and failure listeners for the removeValue() task
        mTask .addOnSuccessListener {
            // If the deletion is successful, show a toast message and redirect to the BusinessListView activity
            Toast.makeText(this, "User data deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, BusinessListView::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            // If the deletion fails, show a toast message with the error message
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    // Function to initialize the views
    private fun initView() {

        tvBusinessId = findViewById(R.id.tvBusinessId)
        tvBusinessName = findViewById(R.id.tvBusinessName)
        tvOwnerName = findViewById(R.id.tvOwnerName)
        tvPostAddress = findViewById(R.id.tvPostAddress)
        tvRegNum = findViewById(R.id.tvRegNum)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    // Function to set the values of the views based on the intent extras
    private fun setValuesToView(){

        tvBusinessId.text = intent.getStringExtra("businessId")
        tvBusinessName.text = intent.getStringExtra("businessName")
        tvOwnerName.text = intent.getStringExtra("ownerName")
        tvPostAddress.text = intent.getStringExtra("postAddress")
        tvRegNum.text = intent.getStringExtra("regNumber")

    }

    // This function opens a dialog that allows the user to update the business data
    private fun openUpdateDialog(
        businessId: String,
        businessName:String
    ){
        // Create the dialog
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog,null)

        mDialog.setView(mDialogView)

        // Get the EditText fields and the button from the dialog
        val etBusinessName = mDialogView.findViewById<EditText>(R.id.tvBusinessName)
        val etOwnerName = mDialogView.findViewById<EditText>(R.id.tvOwnerName)
        val etPostAddress = mDialogView.findViewById<EditText>(R.id.tvPostAddress)
        val etRegNumber = mDialogView.findViewById<EditText>(R.id.tvRegNum)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        // Set the initial values of the EditText fields to the current values of the business data
        etBusinessName.setText(intent.getStringExtra("businessName").toString())
        etOwnerName.setText(intent.getStringExtra("ownerName").toString())
        etPostAddress.setText(intent.getStringExtra("postAddress").toString())
        etRegNumber.setText(intent.getStringExtra("regNumber").toString())

        // Set the title of the dialog to include the name of the business being updated
        mDialog.setTitle("Updating $businessName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        // Set the onClickListener for the update button in the dialog
        btnUpdateData.setOnClickListener{
            // Call the updateBusinessData function to update the data in the Firebase database
            updateBusinessData(
                businessId,
                etBusinessName.text.toString(),
                etOwnerName.text.toString(),
                etPostAddress.text.toString(),
                etRegNumber.text.toString()
            )

            // Display a toast message to confirm that the data has been updated
            Toast.makeText(applicationContext, "Employee Data Updated", Toast.LENGTH_LONG).show()

            // Set the updated data to the correct fields
            tvBusinessId.text = businessId // The business ID should not be changed
            tvBusinessName.text = etBusinessName.text.toString()
            tvOwnerName.text = etOwnerName.text.toString()
            tvPostAddress.text = etPostAddress.text.toString()
            tvRegNum.text = etRegNumber.text.toString()

            alertDialog.dismiss()
        }

    }

    // This function updates the data in the Firebase database for the specified business
    private fun updateBusinessData(
        businessId: String,
        businessName: String,
        ownerName: String,
        postAddress: String,
        regNumber: String
    ){
        // Get a reference to the Firebase database and the "Employees" node
        val dbRef = FirebaseDatabase.getInstance().getReference("Employees").child(businessId)

        // Create a BusinessModel object with the updated data
        val businessInfo = BusinessModel(businessId,businessName,ownerName,postAddress,regNumber)

        // Set the value of the "Employees/businessId" node to the updated BusinessModel object
        dbRef.setValue(businessInfo)
    }

}