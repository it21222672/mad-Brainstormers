// Declare package name and import required libraries
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
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.homepage.Dashboard
import com.example.homepage.R

// Declare the class
class uploading : AppCompatActivity() {

    // Declare variables
    private lateinit var btn_details: Button


    // Override the onCreate method
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

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
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setContentTitle("Submission Received")
            .setContentText("Thank you for submitting your details!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Send the notification
        val notificationId = 1
        notificationManager.notify(notificationId, builder.build())

        // Set the system UI visibility to fullscreen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        // Hide the action bar
        supportActionBar?.hide()

        // Call the super method
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.activity_uploading)

        // Get the reference of the finish button and set a click listener on it
        val btn_finish = findViewById<Button>(R.id.btn_finish);

        btn_finish.setOnClickListener() {
            // Create an intent to start the Dashboard activity
            val intenta = Intent(this, Dashboard::class.java)

            // Start the activity
            startActivity(intenta)

            // Finish the current activity
            finish()
        }

        // Get the reference of the details button and set a click listener on it
        btn_details = findViewById<Button>(R.id.btn_details);

        btn_details.setOnClickListener() {
            // Create an intent to start the BusinessListView activity
            val intenta = Intent(this, BusinessListView::class.java)

            // Start the activity
            startActivity(intenta)

            // Finish the current activity
            finish()
        }

        // Get the reference of the back button and set a click listener on it
        val btn_back = findViewById<Button>(R.id.btn_back);

        btn_back.setOnClickListener() {
            // Create an intent to start the BusinessDetails activity
            val intenta = Intent(this, BusinessDetails::class.java)

            // Start the activity
            startActivity(intenta)

            // Finish the current activity
            finish()
        }
    }
}