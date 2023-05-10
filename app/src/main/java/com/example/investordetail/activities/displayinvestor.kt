package com.example.investordetail.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investordetail.R
import com.example.investordetail.adapters.investadapter
import com.example.investordetail.models.investModel
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.jvm.internal.Ref

class displayinvestor : AppCompatActivity() {
    private lateinit var invesRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var investList: ArrayList<investModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var bttona: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_displayinvestor)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        invesRecyclerView = findViewById(R.id.reinves)

        invesRecyclerView.layoutManager = LinearLayoutManager(this)
        invesRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvloadindData)


        //get invest list array
        investList = arrayListOf<investModel>()


        getEmployeesData()
    }

    private fun getEmployeesData() {

        invesRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        //create the database path
        dbRef = FirebaseDatabase.getInstance().getReference("Investor")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                investList.clear()

                if (snapshot.exists()){
                    for(investsnap in snapshot.children){
                        val invesdata = investsnap.getValue(investModel::class.java)
                        investList.add(invesdata!!)
                    }

                    val mAdapter = investadapter(investList)
                    invesRecyclerView.adapter = mAdapter

                    mAdapter.setonItemclicklistener(object : investadapter.onItemonclicklistener{
                        override fun onItemclick(position: Int) {
                            val intent = Intent(this@displayinvestor , investordetail::class.java )
                            //put extras
                            intent.putExtra("investId", investList[position].investId)
                            intent.putExtra("investName", investList[position].investname)
                            intent.putExtra("investNic", investList[position].investnic)
                            intent.putExtra("investCountry", investList[position].investcountry)
                            startActivity(intent)
                        }


                    })
                    invesRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}