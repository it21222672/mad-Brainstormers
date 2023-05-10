package com.example.firebasekotlin.activities
//imports
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.models.EmployeeModel
import com.example.firebasekotlin.R
import com.example.firebasekotlin.adapters.EmpAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var empList: ArrayList<EmployeeModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching) //set the respective UI(.xml file)

        empRecyclerView = findViewById(R.id.rvEmp) //find the recycleview
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true) //linear layout has a fixed size
        tvLoadingData = findViewById(R.id.tvLoadingData)

        empList = arrayListOf<EmployeeModel>()

        getEmployeesData()//calling the getEmployeesData function
        var emplistback = findViewById<Button>(R.id.emplistback)
        emplistback.setOnClickListener(){
            var intent = Intent(this,EmployeeMainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getEmployeesData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")
        //Add a listener for changes in the data at this location
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) { //A datasnapshot contains data from firebase database location
                empList.clear()
                if (snapshot.exists()){ //Returns true if the snapshot contains a non-null value
                    for (empSnap in snapshot.children){//Gives access to all of the immediate children of this snapshot
                        val empData = empSnap.getValue(EmployeeModel::class.java) //This method is used to marshall the data contained in this snapshot into a class of your choosing
                        empList.add(empData!!)
                    }
                    val mAdapter = EmpAdapter(empList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : EmpAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, EmployeeDetailsActivity::class.java) //calling the employee details activity

                            //put extras - put values at respective places
                            intent.putExtra("empId", empList[position].empId)
                            intent.putExtra("empName", empList[position].empName)
                            intent.putExtra("empTitle", empList[position].empTitle)
                            intent.putExtra("empCnum", empList[position].empCnum)
                            intent.putExtra("empDescription", empList[position].empDescription)
                            startActivity(intent)
                        }
                    })

                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) { //listner either failed or removed
                TODO("Not yet implemented")
            }

        })
    }
}