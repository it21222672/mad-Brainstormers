// This is the package declaration
package com.example.homepage

// Importing necessary dependencies
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

// Declaring the MainActivity class that extends AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Hide the status bar and make the activity full screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        // Call the onCreate method of the superclass and set the layout for this activity
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the reference to the "Start" button from the layout
        val btn_start = findViewById<Button>(R.id.btn_start);

        // Set the onClickListener for the "Start" button
        btn_start.setOnClickListener(){
            // Create an intent to start the Dashboard activity
            val intenta = Intent(this, Dashboard::class.java)
            // Start the Dashboard activity and finish the MainActivity
            startActivity(intenta)
            finish()
        }
    }
}