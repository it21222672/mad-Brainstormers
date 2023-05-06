/**

This class represents the main dashboard activity of the application
 */
package com.example.homepage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.homepage.activity.BusinessDetails

class Dashboard : AppCompatActivity() {
    /**
     * This method is called when the activity is created
     * @param savedInstanceState the saved instance state of the activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // Hide the action bar and set the system UI visibility
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Set up the "Business" button to start the BusinessDetails activity
        val btn_business = findViewById<Button>(R.id.btn_business);

        btn_business.setOnClickListener() {
            val intenta = Intent(this, BusinessDetails::class.java)
            startActivity(intenta)
            finish()
        }
    }
}