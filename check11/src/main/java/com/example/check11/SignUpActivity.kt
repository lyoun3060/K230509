package com.example.check11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.check11.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}