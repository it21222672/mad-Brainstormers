package com.example.investorlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.investorlog.databinding.ActivitySigninBinding
import com.example.investorlog.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signin : AppCompatActivity() {


    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var user: FirebaseAuth
    private lateinit var bttnus: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()


        bttnus = findViewById(R.id.bttnus)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()
        //checkifuserislog()



        bttnus.setOnClickListener(){
            val intent = Intent(this , seting::class.java)
            startActivity(intent)
        }

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener{
            val intent = Intent(this,signup::class.java)
            startActivity(intent)
        }



        /*public fun checkifuserislog() {
            if(user.currentUser != null){
                startActivity(Intent(this , seting::class.java))
                finish()
            }
        }*/

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val passwd = binding.passET.text.toString()

            if(email.isNotEmpty() && passwd.isNotEmpty()){


                    firebaseAuth.signInWithEmailAndPassword(email , passwd).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this , "empty fields are not allowed" , Toast.LENGTH_SHORT).show()

            }
        }
    }


    /*override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }*/

}