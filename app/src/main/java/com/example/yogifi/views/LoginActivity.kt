package com.example.yogifi.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yogifi.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var email : String? = null
    private var password :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnSubmit.setOnClickListener{
            validationCheck()
        }
    }
    //TODO - Proper Authentication Logic Building
    private fun validationCheck(){
        email = binding.email.text.toString()
        password = binding.password.text.toString()
        Log.d("Gopi", "validationCheck: $password")
        if ((email?.isNotEmpty() == true) && (password?.isNotEmpty() == true)) {
            startActivity(
                Intent(
                    this@LoginActivity,
                    HomeActivity::class.java
                )
            )
        } else
        {
            Toast.makeText(this, "Email and Password is compulsory" , Toast.LENGTH_SHORT).show()
        }
    }
}