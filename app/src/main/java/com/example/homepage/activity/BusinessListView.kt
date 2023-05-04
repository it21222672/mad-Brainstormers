//Import necessary packages
package com.example.homepage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homepage.R
import com.example.homepage.adaptors.BusinessAdopter
import com.example.homepage.models.BusinessModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//Declare the activity class and extend AppCompatActivity
class BusinessListView : AppCompatActivity() {

    //Declare variables
    private lateinit var empRecyclerView: RecyclerView
    private lateinit var rvLoadingData: TextView
    private lateinit var empList: ArrayList<BusinessModel>
    private lateinit var dbRef: DatabaseReference

    //Override the onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {

        //Set the status bar to fullscreen and hide the action bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_list_view)

        //Find the RecyclerView and set its layout manager and fixed size
        empRecyclerView = findViewById(R.id.rvbusiness)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)

        //Find the text view for loading data
        rvLoadingData = findViewById(R.id.tvLoadingData)

        //Initialize the employee list array
        empList = arrayListOf<BusinessModel>()

        //Call the function to get business data
        getBusinessData()

    }

    //Function to get the business data from Firebase database
    private fun getBusinessData(){
        //Hide the RecyclerView and show the loading text view
        empRecyclerView.visibility = View.GONE
        rvLoadingData.visibility = View.VISIBLE

        //Get the reference to the database
        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        //Add a value event listener to the reference
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()){
                    //Loop through the data and add it to the employee list
                    for( empSnap in snapshot.children){
                        val empData = empSnap.getValue(BusinessModel::class.java)
                        empList.add(empData!!)
                    }

                    //Create a new adapter with the employee list and set it to the RecyclerView
                    val mAdaptor = BusinessAdopter(empList)
                    empRecyclerView.adapter = mAdaptor

                    //Set a click listener for the adapter items to open the details activity
                    mAdaptor.setOnClickListener(object : BusinessAdopter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@BusinessListView, DetailsView::class.java)

                            //put extras for the clicked item data
                            intent.putExtra("businessId",empList[position].businessId)
                            intent.putExtra("businessName", empList[position].businessName)
                            intent.putExtra("ownerName", empList[position].ownerName)
                            intent.putExtra("postAddress", empList[position].postAddress)
                            intent.putExtra("regNumber", empList[position].regNumber)
                            startActivity(intent)
                        }
                    })

                    //Show the RecyclerView and hide the loading text view
                    empRecyclerView.visibility = View.VISIBLE
                    rvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}