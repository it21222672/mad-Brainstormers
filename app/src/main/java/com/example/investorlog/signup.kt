package com.example.investorlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.investorlog.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signup : AppCompatActivity() {


    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)



        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        supportActionBar?.hide()

        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.textViewa.setOnClickListener{
            val intent = Intent(this,signin::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val passwd = binding.passET.text.toString()
            val confpasd = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && passwd.isNotEmpty() && confpasd.isNotEmpty()){
                if (passwd == confpasd){


                    firebaseAuth.createUserWithEmailAndPassword(email , passwd).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,signin::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this , "password is not match" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this , "empty fields are not allowed" , Toast.LENGTH_SHORT).show()
            }
        }
    }
}