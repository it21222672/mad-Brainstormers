package com.example.homepage.activity


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.homepage.models.BusinessModel
import com.example.homepage.Dashboard
import com.example.homepage.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BusinessDetails : AppCompatActivity() {

    // Initialize views and variables
    private lateinit var editTextTextPersonName : EditText
    private lateinit var editTextTextPersonName2 : EditText
    private lateinit var editTextTextPersonName3 : EditText
    private lateinit var editTextTextPostalAddress : EditText
    private lateinit var btn_Submit: Button

    private lateinit var dbRef :DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        // Hide the action bar and set the layout to fullscreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_details)

        // Find views by ID
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2)
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3)
        editTextTextPostalAddress = findViewById(R.id.editTextTextPostalAddress)
        btn_Submit = findViewById(R.id.btn_Submit)

        // Get a reference to the "Employees" node in the database
        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        // Set click listener for the "Submit" button
        btn_Submit.setOnClickListener{
            saveBusinessData()


            // Notification sending
            // Create a notification channel (required on Android 8.0 and above)
            val channelId = "my_channel_id"
            val channelName = "My Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            // Create an intent for the button action
            val intent = Intent(this, uploading::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            // Build the notification
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.app)
                .setContentTitle("Your Submission Received")
                .setContentText("Thank you for joining with us!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            // Send the notification
            val notificationId = 1
            notificationManager.notify(notificationId, builder.build())
        }

        // Set click listeners for the "Next" and "Back" buttons
        val btn_next = findViewById<Button>(R.id.btn_next);
        btn_next.setOnClickListener() {
            val intenta = Intent(this, uploading::class.java)
            startActivity(intenta)
            finish()
        }

        val btn_back = findViewById<Button>(R.id.btn_back);

        btn_back.setOnClickListener() {
            val intenta = Intent(this, Dashboard::class.java)
            startActivity(intenta)
            finish()
        }
    }

    // Function to save business data to the database
    private fun saveBusinessData() {
        // Get values from the input fields
        val businessName = editTextTextPersonName2.text.toString()
        val ownerName = editTextTextPersonName.text.toString()
        val postAddress = editTextTextPostalAddress.text.toString()
        val regNumber = editTextTextPersonName3.text.toString()

        // Check if all fields are filled
        if (businessName.isEmpty() || ownerName.isEmpty() || postAddress.isEmpty() || regNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        }

        // Generate a unique business ID using push()
        val businessId = dbRef.push().key!!

        // Create a BusinessModel object with the entered data
        val business = BusinessModel(businessId, ownerName, businessName, regNumber, postAddress)

        // Save the data to the database
        dbRef.child(businessId).setValue(business)
            .addOnCompleteListener {
                Toast.makeText(this, "Data Submitted Successfully", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}